package com.example.PAPS.ui.views;

import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Employee;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.EmployeeRepository;
import com.example.PAPS.ui.components.CarComponent;
import com.example.PAPS.ui.components.EmployeeComponent;
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
    private final EmployeeComponent employeeComponent;

    public EmployeeView(EmployeeRepository employeeRepository, EmployeeComponent employeeComponent) {
        this.employeeRepository = employeeRepository;
        this.employeeComponent = employeeComponent;
        this.addNewBtn = new Button("Добавить сотрудника", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(Employee.class);
        this.filter = new TextField();

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, employeeComponent);

        filter.setPlaceholder("Поиск по имени");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listEmployees(e.getValue()));

        grid.setHeight("300px");
        grid.removeColumnByKey("id");
        grid.getColumnByKey("surname").setHeader("Фамилия").setAutoWidth(true);
        grid.getColumnByKey("name").setHeader("Имя").setAutoWidth(true);
        grid.getColumnByKey("patronymic").setHeader("Отчество").setAutoWidth(true);
        grid.getColumnByKey("dateOfBirth").setHeader("Дата рождения").setAutoWidth(true);
        grid.removeColumnByKey("sex");
        grid.getColumnByKey("passportID").setHeader("Серия и номер паспорта").setAutoWidth(true);
        grid.getColumnByKey("INN").setHeader("ИНН").setAutoWidth(true);
        grid.getColumnByKey("SNILS").setHeader("СНИЛС").setAutoWidth(true);
        grid.getColumnByKey("dateOfEmployment").setHeader("Дата трудоустройства").setAutoWidth(true);
        grid.getColumnByKey("dateOfDismissal").setHeader("Дата увольнения").setAutoWidth(true);
        grid.getColumnByKey("phone").setHeader("Телефон").setAutoWidth(true);
        grid.getColumnByKey("email").setHeader("Эл. почта").setAutoWidth(true);
        grid.removeColumnByKey("departmentCode");
        grid.removeColumnByKey("positionCode");

        grid.asSingleSelect().addValueChangeListener(e -> employeeComponent.editEmployee(e.getValue()));

        addNewBtn.addClickListener(e -> employeeComponent.editEmployee(new Employee()));

        employeeComponent.setChangeHandler(() -> {
            employeeComponent.setVisible(false);
            listEmployees(filter.getValue());
        });

        listEmployees("");
    }

    private void listEmployees(String surname) {
        if (!StringUtils.hasText(surname))
            grid.setItems(employeeRepository.findAll());
        else
            grid.setItems(employeeRepository.findAll().stream().filter(employee -> employee.getName().equals(surname)).collect(Collectors.toList()));
    }
}
