package com.example.PAPS.repositories;

import com.example.PAPS.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByPassport(String passport);
    List<Client> findAllByPassport(String passport);
    List<Client> findAll();
}
