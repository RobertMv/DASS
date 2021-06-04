package com.example.PAPS.ui.views;

import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Client;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.ClientRepository;
import com.example.PAPS.ui.components.CarComponent;
import com.example.PAPS.ui.components.ClientComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Route
@PageTitle("Автомобили")
public class CarView extends VerticalLayout {

    private final CarRepository carRepository;
    private final CarComponent carComponent;
    private final Grid<Car> grid;
    private final Button addNewBtn;
    private final TextField filter;

    public CarView(CarRepository carRepository, CarComponent carComponent) {
        this.carRepository = carRepository;
        this.carComponent = carComponent;
        this.addNewBtn = new Button("Добавить автомобиль", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(Car.class);
        this.filter = new TextField();

        filter.setMaxLength(17);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid);

        filter.setPlaceholder("Поиск по vin");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCars(e.getValue()));

        grid.setHeight("300px");
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("owner");
        grid.removeColumnByKey("ownerPassport");
        grid.getColumnByKey("vin").setHeader("VIN").setAutoWidth(true);
        grid.getColumnByKey("model").setHeader("Модель");
        grid.getColumnByKey("color").setHeader("Цвет");
        grid.getColumnByKey("dateOfManufacture").setHeader("Дата производства");
        grid.getColumnByKey("price").setHeader("Цена");
        grid.addColumn(car -> car.getOwner().getFio()).setHeader("ФИО владельца");
        grid.addColumn(car -> car.getOwner().getPassport()).setHeader("Срия и номер паспорта владельца");

        grid.asSingleSelect().addValueChangeListener(e -> {
            try {
                carComponent.editCar(e.getValue());
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });

        addNewBtn.addClickListener(e -> {
            try {
                carComponent.editCar(new Car());
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });

        carComponent.setChangeHandler(() -> {
            carComponent.setVisible(false);
            listCars(filter.getValue());
        });

        listCars("");
    }

    private void listCars(String vin) {
        if (!StringUtils.hasText(vin))
            grid.setItems(carRepository.findAll());
        else
            grid.setItems(carRepository.findAll().stream().filter(car -> car.getVin().equals(vin)).collect(Collectors.toList()));
    }
}
