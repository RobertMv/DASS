package com.example.PAPS.ui.views;

import com.example.PAPS.entities.Client;
import com.example.PAPS.repositories.ClientRepository;
import com.example.PAPS.ui.components.ClientComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

import java.awt.*;

@Route
@PageTitle("Мои клиенты")
public class ClientView extends VerticalLayout {

    private final ClientRepository clientRepository;
    private final ClientComponent clientComponent;
    private final Grid<Client> grid;
    private final Button addNewBtn;
    private final TextField filter;

    public ClientView(ClientRepository clientRepository, ClientComponent clientComponent) {
        this.clientRepository = clientRepository;
        this.clientComponent = clientComponent;
        this.addNewBtn = new Button("Новый клиент", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(Client.class);
        this.filter = new TextField();

        filter.setMaxLength(10);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, clientComponent);

        filter.setPlaceholder("Поиск по паспорту");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listClients(e.getValue()));

        grid.setHeight("300px");
        grid.removeColumnByKey("id");
        grid.getColumnByKey("fio").setHeader("ФИО");
        grid.getColumnByKey("address").setHeader("Адрес");
        grid.getColumnByKey("passport").setHeader("Серия и номер паспорта");

        grid.asSingleSelect().addValueChangeListener(e -> {
            clientComponent.editClient(e.getValue());
        });

        addNewBtn.addClickListener(e -> clientComponent.editClient(new Client()));

        clientComponent.setChangeHandler(() -> {
            clientComponent.setVisible(false);
            listClients(filter.getValue());
        });

        listClients("");
    }

    private void listClients(String passportId) {
        if (!StringUtils.hasText(passportId))
            grid.setItems(clientRepository.findAll());
        else
            grid.setItems(clientRepository.findAllByPassport(passportId));
    }
}