package com.example.PAPS.ui.components;

import com.example.PAPS.entities.Client;
import com.example.PAPS.repositories.ClientRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ClientComponent extends VerticalLayout implements KeyNotifier {

    private final ClientRepository clientRepository;

    private Client client;

    TextField fio = new TextField("ФИО");
    TextField address = new TextField("Адрес");
    TextField passport = new TextField("Серия и номер паспорта");

    Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    Button cancel = new Button("Закрыть");
    Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Client> binder = new Binder<>(Client.class);
    private ChangeHandler changeHandler;

    @Autowired
    public ClientComponent(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

        passport.setMaxLength(10);
        add(fio, address, passport, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editClient(client));
        setVisible(false);
    }

    void delete() {
        clientRepository.delete(client);
        changeHandler.onChange();
    }

    void save() {
        clientRepository.save(client);
        changeHandler.onChange();
    }

    public final void editClient(Client c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        if (c.getId() != null)
            this.client = clientRepository.findById(c.getId()).orElse(c);
        else
            this.client = c;

        binder.setBean(client);

        setVisible(true);

        fio.focus();
    }
    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}