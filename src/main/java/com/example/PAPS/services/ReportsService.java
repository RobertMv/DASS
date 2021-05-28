package com.example.PAPS.services;

import org.springframework.stereotype.Service;

@Service
public class ReportsService {

    public String getOrdersReport(){
        return "orders report";
    }

    public String getSellsReport(){
        return "sells report";
    }

    public String getMaterialConsumptionReport(){
        return "material consumption report";
    }

    public String getSupplementReport(){
        return "supplement report";
    }
}
