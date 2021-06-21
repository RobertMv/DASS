package com.example.PAPS.services;

import com.example.PAPS.entities.CarsReport;
import com.example.PAPS.entities.MaintenanceOrder;
import com.example.PAPS.entities.SparesReport;
import com.example.PAPS.entities.SupplementsReport;
import com.example.PAPS.repositories.CarsReportRepository;
import com.example.PAPS.repositories.MaintenanceOrderRepository;
import com.example.PAPS.repositories.SparesReportRepository;
import com.example.PAPS.repositories.SupplementReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    private final CarsReportRepository carsReportRepository;
    private final SparesReportRepository sparesReportRepository;
    private final SupplementReportRepository supplementReportRepository;
    private final MaintenanceOrderRepository orderRepository;

    public ReportsService(CarsReportRepository carsReportRepository, SparesReportRepository sparesReportRepository, SupplementReportRepository supplementReportRepository, MaintenanceOrderRepository orderRepository) {
        this.carsReportRepository = carsReportRepository;
        this.sparesReportRepository = sparesReportRepository;
        this.supplementReportRepository = supplementReportRepository;
        this.orderRepository = orderRepository;
    }

    public List<MaintenanceOrder> getOrdersReport(){
        return orderRepository.findAllByIsDoneIsTrue();
    }

    public List<CarsReport> getSellsReport(){
        return carsReportRepository.findAll();
    }

    public List<SparesReport> getMaterialConsumptionReport(){
        return sparesReportRepository.findAll();
    }

    public List<SupplementsReport> getSupplementReport(){
        return supplementReportRepository.findAll();
    }
}
