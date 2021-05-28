package com.example.PAPS.repositories;

import com.example.PAPS.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByPassportID(String passportID);
}
