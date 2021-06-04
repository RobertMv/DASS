package com.example.PAPS.ui.components;

import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Client;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.ClientRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@SpringComponent
@UIScope
public class CarComponent extends VerticalLayout implements KeyNotifier {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    private Car car;

    TextField vin = new TextField("VIN");
    TextField model = new TextField("Модель");
    TextField color = new TextField("Цвет");
    TextField ownerPassport = new TextField("Серия и номер паспорта владельца");
    BigDecimalField price = new BigDecimalField("Цена");
    TextField ownerName = new TextField("ФИО владельца");
    DatePicker dateOfManufacture = new DatePicker("Дата производства");

    Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    Button cancel = new Button("Закрыть");
    Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Car> binder = new Binder<>(Car.class);
    private ClientComponent.ChangeHandler changeHandler;

    @Autowired
    public CarComponent(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;

        vin.setMaxLength(17);
        add(vin, model, color, price, ownerName, ownerPassport, dateOfManufacture, actions);

        binder.forField(vin).bind(Car::getVin, Car::setVin);
        binder.forField(model).bind(Car::getModel, Car::setModel);
        binder.forField(color).bind(Car::getColor, Car::setColor);
        binder.forField(price)
                .withConverter(
                        BigDecimal::doubleValue,
                        BigDecimal::new,
                        "Should be a float")
                .bind(Car::getPrice, Car::setPrice);
        binder.forField(ownerName).bind(car1 -> car1.getOwner().getFio(), (car1, s) -> car1.setOwner(clientRepository.findClientByPassport(car1.getOwnerPassport())));
        binder.forField(ownerPassport).bind(Car::getOwnerPassport, Car::setOwnerPassport);
        binder.forField(dateOfManufacture).bind(Car::getDateOfManufacture, Car::setDateOfManufacture);
        //binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> {
            try {
                save();
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });

        save.addClickListener(e -> {
            try {
                save();
                binder.writeBean(this.car);
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> {
            try {
                editCar(car);
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });
        setVisible(false);
    }

    void delete() {
        carRepository.delete(car);
        changeHandler.onChange();
    }

    void save() throws ValidationException {
        car.setOwner(clientRepository.findClientByPassport(ownerPassport.getValue()));
        car.setPrice(price.getValue().doubleValue());
        car.setDateOfManufacture(dateOfManufacture.getValue());
        carRepository.save(car);
        changeHandler.onChange();
    }

    public final void editCar(Car c) throws ValidationException {
        setVisible(true);
        if (c == null) {
            setVisible(false);
            return;
        }
        if (c.getId() != null)
            this.car = carRepository.findById(c.getId()).orElse(c);
        else
            this.car = c;

        binder.readBean(this.car);

        vin.focus();
    }
    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ClientComponent.ChangeHandler h) {
        changeHandler = h;
    }
}
