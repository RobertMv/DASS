package com.example.PAPS.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportsFormationController {


    @GetMapping("/orders")
    public String getOrdersReport(){
        return "maintenanceOrderService.getReport()";
    }

    @GetMapping("/sells")
    public String getSellsReport(){
        return "sellingService.getReport()";
    }

    @GetMapping("/materials")
    public String getMaterialsConsumption(){
        return "orderService.getConsumptionReport()";
    }

    @GetMapping("/supply")
    public String getSupplementReport(){
        return "supplierService.getReport()";
    }
}
