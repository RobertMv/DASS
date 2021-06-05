package com.example.PAPS.ui.views;

import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Employee;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.EmployeeRepository;
import com.example.PAPS.ui.components.CarComponent;
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
@PageTitle("Сотрудники")
public class EmployeeView extends VerticalLayout {

    private final EmployeeRepository employeeRepository;
    private final Grid<Employee> grid;
    private final Button addNewBtn;
    private final TextField filter;

    public EmployeeView(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.addNewBtn = new Button("Добавить сотрудника", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(Employee.class);
        this.filter = new TextField();

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid);

        filter.setPlaceholder("Поиск по ФИО");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listEmployees(e.getValue()));

        grid.setHeight("300px");
        grid.removeColumnByKey("id");
        grid.getColumnByKey("surname").setHeader("Фамилия");
        grid.getColumnByKey("name").setHeader("Имя");
        grid.getColumnByKey("patronymic").setHeader("Отчество");
        grid.getColumnByKey("dateOfBirth").setHeader("Дата рождения");
        grid.removeColumnByKey("sex");
        grid.getColumnByKey("passportID").setHeader("Серия и номер паспорта");
        grid.getColumnByKey("INN").setHeader("ИНН");
        grid.getColumnByKey("SNILS").setHeader("СНИЛС");
        grid.getColumnByKey("dateOfEmployment").setHeader("Дата трудоустройства");
        grid.removeColumnByKey("dateOfDismissal");
        grid.getColumnByKey("phone").setHeader("Телефон");
        grid.getColumnByKey("email").setHeader("Эл. почта");
        grid.removeColumnByKey("departmentCode");
        grid.removeColumnByKey("positionCode");

//        grid.asSingleSelect().addValueChangeListener(e -> {
//            try {
//                carComponent.editCar(e.getValue());
//            } catch (ValidationException validationException) {
//                validationException.printStackTrace();
//            }
//        });
//
//        addNewBtn.addClickListener(e -> {
//            try {
//                carComponent.editCar(new Car());
//
//            } catch (ValidationException validationException) {
//                validationException.printStackTrace();
//            }
//        });
//
//        carComponent.setChangeHandler(() -> {
//            carComponent.setVisible(false);
//            listCars(filter.getValue());
//        });

        listEmployees("");
    }

    private void listEmployees(String surname) {
        if (!StringUtils.hasText(surname))
            grid.setItems(employeeRepository.findAll());
        else
            grid.setItems(employeeRepository.findAll().stream().filter(employee -> employee.getSurname().equals(surname)).collect(Collectors.toList()));
    }
}
