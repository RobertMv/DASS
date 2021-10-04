package com.example.PAPS.ui.components;

import com.example.PAPS.entities.Insurance;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.InsuranceRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class InsuranceComponent  extends VerticalLayout implements KeyNotifier {
    private final InsuranceRepository insuranceRepository;
    private final CarRepository carRepository;

    private Insurance insurance;

    TextField series = new TextField("Серия");
    TextField number = new TextField("Номер");
    Select<String> typeSelector = new Select<>("КАСКО", "ОСАГО");
    Select<String> kindSelector = new Select<>("Базовая", "Повышенная", "Полная");
    TextField car = new TextField("VIN автомобиля");

    Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    Button cancel = new Button("Закрыть");
    Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Insurance> binder = new Binder<>(Insurance.class);
    private InsuranceComponent.ChangeHandler changeHandler;


    public InsuranceComponent(InsuranceRepository insuranceRepository, CarRepository carRepository) {
        this.insuranceRepository = insuranceRepository;
        this.carRepository = carRepository;
        typeSelector.setPlaceholder("Тип страховки");
        kindSelector.setPlaceholder("Вид страховки");

        series.setMaxLength(10);
        number.setMaxLength(10);

        add(series, number, typeSelector, kindSelector, car, actions);

        binder.forField(series).bind(Insurance::getSeries, Insurance::setSeries);
        binder.forField(number).bind(Insurance::getNumber, Insurance::setNumber);
        binder.forField(typeSelector).bind(Insurance::getType, Insurance::setType);
        binder.forField(kindSelector).bind(Insurance::getKind, Insurance::setKind);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> edit(insurance));
        setVisible(false);
    }
    void delete() {
        insuranceRepository.delete(insurance);
        changeHandler.onChange();
    }

    void save() {
        insuranceRepository.save(insurance);
        changeHandler.onChange();
    }

    public final void edit(Insurance c){
        if (c == null) {
            setVisible(false);
            return;
        }
        if (c.getId() != null)
            this.insurance = insuranceRepository.findById(c.getId()).orElse(c);
        else
            this.insurance = c;

        insurance.setCar(carRepository.findCarByVin(car.getValue()));
        binder.setBean(insurance);

        setVisible(true);

        series.focus();
    }
    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(InsuranceComponent.ChangeHandler h) {
        changeHandler = h;
    }
}
