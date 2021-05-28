package com.example.PAPS.controllers;

import com.example.PAPS.services.ReportsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportsFormationController {

    private final ReportsService reportsService;

    public ReportsFormationController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("/orders")
    public String getOrdersReport() {
        return reportsService.getOrdersReport();
    }

    @GetMapping("/sells")
    public String getSellsReport() {
        return reportsService.getSellsReport();
    }

    @GetMapping("/materials")
    public String getMaterialsConsumption() {
        return reportsService.getMaterialConsumptionReport();
    }

    @GetMapping("/supply")
    public String getSupplementReport() {
        return reportsService.getSupplementReport();
    }
}
