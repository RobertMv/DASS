package com.example.PAPS.ui.components;

import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Employee;
import com.example.PAPS.repositories.EmployeeRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class EmployeeComponent  extends VerticalLayout implements KeyNotifier {
    private final EmployeeRepository employeeRepository;

    private Employee employee;

    TextField surname = new TextField("Фамилия");
    TextField patronymic = new TextField("Отчество");
    TextField name = new TextField("Имя");
    TextField sex = new TextField("Пол");
    TextField phone = new TextField("Телефон");
    TextField email = new TextField("Эл. почта");
    TextField passport = new TextField("Серия и номер паспорта");
    TextField inn = new TextField("ИНН");
    TextField snils = new TextField("СНИЛС");
    TextField departmentCode = new TextField("Код подразделения");
    TextField positionCode = new TextField("Код должности");
    DatePicker dateOfEmployment = new DatePicker("Дата найма");
    DatePicker dateOfDismissal = new DatePicker("Дата увольнения");
    DatePicker dateOfBirth = new DatePicker("Дата рождения");

    Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    Button cancel = new Button("Закрыть");
    Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Employee> binder = new Binder<>(Employee.class);
    private EmployeeComponent.ChangeHandler changeHandler;


    public EmployeeComponent(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

        sex.setMaxLength(1);
        phone.setMaxLength(11);
        passport.setMaxLength(10);
        inn.setMaxLength(12);
        snils.setMaxLength(11);
        add(name, surname, patronymic, dateOfBirth, sex, phone, email, passport, inn, snils, departmentCode, positionCode, dateOfEmployment, dateOfDismissal, actions);

        binder.forField(name).bind(Employee::getName, Employee::setName);
        binder.forField(surname).bind(Employee::getSurname, Employee::setSurname);
        binder.forField(patronymic).bind(Employee::getPatronymic, Employee::setPatronymic);
        binder.forField(sex).bind(Employee::getSex, Employee::setSex);
        binder.forField(phone).bind(Employee::getPhone, Employee::setPhone);
        binder.forField(email).bind(Employee::getEmail, Employee::setEmail);
        binder.forField(passport)
                .withNullRepresentation("")
                .withConverter(new StringToLongConverter("Только цифры!"))
                .bind(Employee::getPassportID, Employee::setPassportID);
        binder.forField(inn)
                .withNullRepresentation("")
                .withConverter(new StringToLongConverter("Только цифры!"))
                .bind(Employee::getINN, Employee::setINN);
        binder.forField(snils)
                .withNullRepresentation("")
                .withConverter(new StringToLongConverter("Только цифры!"))
                .bind(Employee::getSNILS, Employee::setSNILS);
        binder.forField(departmentCode).bind(Employee::getDepartmentCode, Employee::setDepartmentCode);
        binder.forField(positionCode).bind(Employee::getPositionCode, Employee::setPositionCode);
        binder.forField(dateOfBirth).bind(Employee::getDateOfBirth, Employee::setDateOfBirth);
        binder.forField(dateOfDismissal).bind(Employee::getDateOfDismissal, Employee::setDateOfDismissal);
        binder.forField(dateOfEmployment).bind(Employee::getDateOfEmployment, Employee::setDateOfEmployment);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editEmployee(employee));
        setVisible(false);
    }
    void delete() {
        //Employee emp = employeeRepository.findByEmail(employee.getEmail());
        employeeRepository.delete(employee);
        changeHandler.onChange();
    }

    void save() {

        employeeRepository.save(employee);
        changeHandler.onChange();
    }

    public final void editEmployee(Employee c){
        if (c == null) {
            setVisible(false);
            return;
        }
        if (c.getId() != null)
            this.employee = employeeRepository.findById(c.getId()).orElse(c);
        else
            this.employee = c;

        binder.setBean(employee);

        setVisible(true);

        name.focus();
    }
    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(EmployeeComponent.ChangeHandler h) {
        changeHandler = h;
    }
}
