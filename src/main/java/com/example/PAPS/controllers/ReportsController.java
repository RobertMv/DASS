package com.example.PAPS.controllers;

import com.example.PAPS.entities.CarsReport;
import com.example.PAPS.entities.MaintenanceOrder;
import com.example.PAPS.entities.SparesReport;
import com.example.PAPS.entities.SupplementsReport;
import com.example.PAPS.services.ReportsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SERVICE_MANAGER"})
    @GetMapping("/orders")
    public List<MaintenanceOrder> getOrdersReport() {
        return reportsService.getOrdersReport();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_CAR_SELLING_MANAGER"})
    @GetMapping("/sells")
    public List<CarsReport> getSellsReport() {
        return reportsService.getSellsReport();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SERVICE_MANAGER"})
    @GetMapping("/materials")
    public List<SparesReport> getMaterialsConsumption() {
        return reportsService.getMaterialConsumptionReport();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SUPPLIER_D"})
    @GetMapping("/supply")
    public List<SupplementsReport> getSupplementReport() {
        return reportsService.getSupplementReport();
    }
}
