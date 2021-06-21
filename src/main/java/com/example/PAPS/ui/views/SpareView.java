package com.example.PAPS.ui.views;

import com.example.PAPS.entities.Client;
import com.example.PAPS.entities.Spare;
import com.example.PAPS.repositories.ClientRepository;
import com.example.PAPS.repositories.SpareRepository;
import com.example.PAPS.ui.components.ClientComponent;
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
@PageTitle("Запчасти")
public class SpareView extends VerticalLayout {

    private final SpareRepository spareRepository;
    private final Grid<Spare> grid;
    private final Button addNewBtn;
    private final TextField filter;

    public SpareView(SpareRepository spareRepository) {
        this.spareRepository = spareRepository;
        this.addNewBtn = new Button("Добавить запчасть", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(Spare.class);
        this.filter = new TextField();

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid);

        filter.setPlaceholder("Поиск по названию");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listClients(e.getValue()));

        grid.setHeight("300px");
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("supplier");
        grid.removeColumnByKey("vendorCode");
        grid.getColumnByKey("name").setHeader("Название");
        grid.getColumnByKey("code").setHeader("Артикул");
        grid.addColumn(spare -> spare.getSupplier().getName()).setHeader("Поставщик");

//        grid.asSingleSelect().addValueChangeListener(e -> {
//            clientComponent.editClient(e.getValue());
//        });
//
//        addNewBtn.addClickListener(e -> clientComponent.editClient(new Client()));
//
//        clientComponent.setChangeHandler(() -> {
//            clientComponent.setVisible(false);
//            listClients(filter.getValue());
//        });

        listClients("");
    }

    private void listClients(String name) {
        if (!StringUtils.hasText(name))
            grid.setItems(spareRepository.findAll());
        else
            grid.setItems(spareRepository.findAll().stream().filter(spare -> spare.getName().equals(name)));
    }
}
