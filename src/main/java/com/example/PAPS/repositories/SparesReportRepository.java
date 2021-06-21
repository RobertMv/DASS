package com.example.PAPS.repositories;

import com.example.PAPS.entities.Spare;
import com.example.PAPS.entities.SparesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SparesReportRepository extends JpaRepository<SparesReport, Long> {
    List<SparesReport> findAllByMonth(String month);
}
