package com.example.PAPS.services;

import com.example.PAPS.dtos.MaintenanceOrderDto;
import com.example.PAPS.entities.MaintenanceOrder;
import com.example.PAPS.repositories.MaintenanceOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final MaintenanceOrderRepository orderRepository;
    private final DtoEntityConversionService conversionService;

    public OrderService(MaintenanceOrderRepository orderRepository, DtoEntityConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.conversionService = conversionService;
    }

    public void add(MaintenanceOrderDto orderDto) {
        MaintenanceOrder order = conversionService.convert(orderDto);
        orderRepository.save(order);
    }

    public List<MaintenanceOrder> getToDoOrders(){
        return orderRepository.findAllByIsDoneIsFalse();
    }

    public List<MaintenanceOrder> getAllOrders(){
        return orderRepository.findAll();
    }

    public void orderIsDone(MaintenanceOrderDto orderDto){
        MaintenanceOrder order = orderRepository.findById(orderDto.getId()).get();
        order.setDone(true);
        orderRepository.save(order);
    }
}
