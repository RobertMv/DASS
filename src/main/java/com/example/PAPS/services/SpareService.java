package com.example.PAPS.services;

import com.example.PAPS.dtos.SpareDto;
import com.example.PAPS.entities.Spare;
import com.example.PAPS.entities.SparesReport;
import com.example.PAPS.repositories.SpareRepository;
import com.example.PAPS.repositories.SparesReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpareService {
    private final SpareRepository spareRepository;
    private final SparesReportRepository sparesReportRepository;
    private final DtoEntityConversionService dtoEntityConversionService;

    public SpareService(SpareRepository spareRepository, SparesReportRepository sparesReportRepository, DtoEntityConversionService dtoEntityConversionService) {
        this.spareRepository = spareRepository;
        this.sparesReportRepository = sparesReportRepository;
        this.dtoEntityConversionService = dtoEntityConversionService;
    }

    public void add(SpareDto spareDto){
        Spare spare = dtoEntityConversionService.convert(spareDto);
        spareRepository.save(spare);
    }

    public List<Spare> getAll(){
        return spareRepository.findAll();
    }

    public void sell(SpareDto spareDto, Integer amount){
        Spare spare = spareRepository.findByVendorCode(spareDto.getVendorCode());
        spare.setAmount(spare.getAmount()-amount);
        spareRepository.save(spare);

        SparesReport report = new SparesReport();
        report.setCode(spare.getCode());
        report.setName(spare.getName());
        report.setSupplier(spareDto.getSupplier());
        report.setVendorCode(spare.getVendorCode());
        report.setAmount(report.getAmount()+amount);
        sparesReportRepository.save(report);
    }
}
