package com.example.PAPS.services;

import com.example.PAPS.dtos.SpareDto;
import com.example.PAPS.entities.Spare;
import com.example.PAPS.repositories.SpareRepository;
import org.springframework.stereotype.Service;

@Service
public class SpareService {
    private final SpareRepository spareRepository;
    private final DtoEntityConversionService dtoEntityConversionService;

    public SpareService(SpareRepository spareRepository, DtoEntityConversionService dtoEntityConversionService) {
        this.spareRepository = spareRepository;
        this.dtoEntityConversionService = dtoEntityConversionService;
    }

    public void add(SpareDto spareDto){
        Spare spare = new Spare();
        spare = dtoEntityConversionService.convert(spareDto);
    }

}
