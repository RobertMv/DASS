package com.example.PAPS.repositories;

import com.example.PAPS.entities.CarsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsReportRepository extends JpaRepository<CarsReport, Long> {
    List<CarsReport> findAll();
}
