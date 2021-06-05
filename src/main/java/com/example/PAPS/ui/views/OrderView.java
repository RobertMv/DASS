package com.example.PAPS.ui.views;

import com.example.PAPS.entities.Client;
import com.example.PAPS.entities.MaintenanceOrder;
import com.example.PAPS.repositories.ClientRepository;
import com.example.PAPS.repositories.MaintenanceOrderRepository;
import com.example.PAPS.ui.components.ClientComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

import static java.lang.Boolean.parseBoolean;

@Route
@PageTitle("Заказы")
public class OrderView extends VerticalLayout {

    private final MaintenanceOrderRepository orderRepository;
    private final Grid<MaintenanceOrder> grid;
    private final Button addNewBtn;
    private final TextField filter;

    public OrderView(MaintenanceOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.addNewBtn = new Button("Новый клиент", VaadinIcon.PLUS.create());
        this.grid = new Grid<>(MaintenanceOrder.class);
        this.filter = new TextField();

        filter.setMaxLength(10);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid);

        filter.setPlaceholder("Поиск по паспорту");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listClients(parseBoolean(e.getValue())));

        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("");
        checkbox.setValue(true);

        grid.setHeight("300px");
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("car");
        grid.getColumnByKey("dateOfMaintenance").setHeader("Дата прохождения ТО");
        grid.getColumnByKey("price").setHeader("Цена");
        grid.getColumnByKey("description").setHeader("Описание заказа");
        grid.getColumnByKey("isDone").setEditorComponent(checkbox).setHeader("Выполнено");
        grid.addColumn(order -> order.getCar().getModel()).setHeader("Автомобиль");

        checkbox.addValueChangeListener(event -> {
            MaintenanceOrder order = orderRepository.findById(Long.parseLong(grid.getId().get())).get();
            orderRepository.save(order);
        });

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

        listClients(true);
    }

    private void listClients(boolean isDone) {
        if (isDone)
            grid.setItems(orderRepository.findAllByIsDoneIsTrue());
        else
            grid.setItems(orderRepository.findAll());
    }
}
