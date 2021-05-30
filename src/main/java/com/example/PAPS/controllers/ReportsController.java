package com.example.PAPS.controllers;

import com.example.PAPS.services.ReportsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SERVICE_MANAGER"})
    @GetMapping("/orders")
    public String getOrdersReport() {
        return reportsService.getOrdersReport();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_AUTO_SELLING_MANAGER"})
    @GetMapping("/sells")
    public String getSellsReport() {
        return reportsService.getSellsReport();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SERVICE_MANAGER"})
    @GetMapping("/materials")
    public String getMaterialsConsumption() {
        return reportsService.getMaterialConsumptionReport();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SUPPLIER_D"})
    @GetMapping("/supply")
    public String getSupplementReport() {
        return reportsService.getSupplementReport();
    }
}
