package com.example.PAPS.ui.components;

import com.example.PAPS.entities.Car;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.ClientRepository;
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
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

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
    TextField price = new TextField("Цена");
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
                .withNullRepresentation("")
                .withConverter(new StringToDoubleConverter("Цена должна быть в формате ##.##"))
                .bind(Car::getPrice, Car::setPrice);
        //binder.forField(ownerName).withNullRepresentation("").bind(car1 -> car1.getOwner().getFio(), (car1, s) -> car1.setOwner(clientRepository.findClientByPassport(car1.getOwnerPassport())));
        binder.forField(ownerPassport).bind(Car::getOwnerPassport, Car::setOwnerPassport);
        binder.forField(dateOfManufacture).bind(Car::getDateOfManufacture, Car::setDateOfManufacture);
        //binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCar(car));
        setVisible(false);
    }

    void delete() {
        carRepository.delete(car);
        changeHandler.onChange();
    }

    void save() {
        car.setOwner(clientRepository.findClientByPassport(ownerPassport.getValue()));
        car.setPrice(Double.parseDouble(price.getValue()));
        car.setDateOfManufacture(dateOfManufacture.getValue());
        carRepository.save(car);
        changeHandler.onChange();
    }

    public final void editCar(Car c){
        if (c == null) {
            setVisible(false);
            return;
        }
        if (c.getId() != null)
            this.car = carRepository.findById(c.getId()).orElse(c);
        else
            this.car = c;

        binder.setBean(car);

        setVisible(true);

        vin.focus();
    }
    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ClientComponent.ChangeHandler h) {
        changeHandler = h;
    }

    private Car getTestCar(){
        Car mycar = new Car();
        mycar.setVin("717123412847374");
        mycar.setModel("Tesla model S");
        mycar.setColor("Gray");
        mycar.setDateOfManufacture(LocalDate.now());
        mycar.setPrice(59990.00);
        mycar.setOwner(clientRepository.findClientByPassport("9214756693"));
        mycar.setOwnerPassport("9214756693");
        return car;
    }
}
