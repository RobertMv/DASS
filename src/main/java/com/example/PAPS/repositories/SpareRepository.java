package com.example.PAPS.repositories;

import com.example.PAPS.entities.Spare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpareRepository extends JpaRepository<Spare, Long> {
    Spare findByVendorCode(Long vendorCode);
}
