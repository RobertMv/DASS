package com.example.PAPS.ui.views;

import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Insurance;
import com.example.PAPS.repositories.InsuranceRepository;
import com.example.PAPS.ui.components.InsuranceComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route
@PageTitle("Страховки")
public class InsuranceView extends VerticalLayout {

    private final InsuranceRepository insuranceRepository;
    private final Grid<Insurance> grid;
    private final InsuranceComponent insuranceComponent;
    private final Button addNewBtn;
    private final TextField filter;

    public InsuranceView(InsuranceRepository insuranceRepository, InsuranceComponent insuranceComponent) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceComponent = insuranceComponent;
        this.addNewBtn = new Button("Зарегистрировать страховку", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(Insurance.class);
        this.filter = new TextField();

        filter.setMaxLength(17);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, this.insuranceComponent);

        filter.setPlaceholder("Поиск по номеру");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> insurances(e.getValue()));

        grid.setHeight("300px");
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("car");
        grid.getColumnByKey("series").setHeader("Серия").setAutoWidth(true);
        grid.getColumnByKey("number").setHeader("Номер").setAutoWidth(true);
        grid.getColumnByKey("type").setHeader("Тип").setAutoWidth(true);
        grid.getColumnByKey("kind").setHeader("Вид").setAutoWidth(true);
        grid.addColumn(insurance -> insurance.getCar().getVin()).setHeader("VIN автомобиля");

        grid.asSingleSelect().addValueChangeListener(e -> insuranceComponent.edit(e.getValue()));

        addNewBtn.addClickListener(e -> insuranceComponent.edit(new Insurance()));

        insuranceComponent.setChangeHandler(() -> {
            insuranceComponent.setVisible(false);
            insurances(filter.getValue());
        });

        insurances("");
    }

    private void insurances(String number) {
        if (!StringUtils.hasText(number))
            grid.setItems(insuranceRepository.findAll());
        else
            grid.setItems(insuranceRepository.findAll().stream().filter(insurance -> insurance.getNumber().equals(number)));
    }
}
