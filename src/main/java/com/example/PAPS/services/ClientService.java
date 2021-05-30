package com.example.PAPS.services;

import com.example.PAPS.dtos.ClientDto;
import com.example.PAPS.entities.Client;
import com.example.PAPS.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final DtoEntityConversionService conversionService;

    public ClientService(ClientRepository clientRepository, DtoEntityConversionService conversionService) {
        this.clientRepository = clientRepository;
        this.conversionService = conversionService;
    }

    public void add(ClientDto clientDto){
        Client client = conversionService.convert(clientDto);
        clientRepository.save(client);
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }
}
