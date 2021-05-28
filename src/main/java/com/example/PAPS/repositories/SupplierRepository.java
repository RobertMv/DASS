package com.example.PAPS.repositories;

import com.example.PAPS.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findSupplierByName(String name);

    List<Supplier> findAll();
}
