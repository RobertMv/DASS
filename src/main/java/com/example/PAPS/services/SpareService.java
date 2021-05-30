package com.example.PAPS.services;

import com.example.PAPS.dtos.SpareDto;
import com.example.PAPS.entities.Spare;
import com.example.PAPS.repositories.SpareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpareService {
    private final SpareRepository spareRepository;
    private final DtoEntityConversionService dtoEntityConversionService;

    public SpareService(SpareRepository spareRepository, DtoEntityConversionService dtoEntityConversionService) {
        this.spareRepository = spareRepository;
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
        spare.setAmount(spareDto.getVendorCode()-amount);
        spareRepository.save(spare);
    }

}
