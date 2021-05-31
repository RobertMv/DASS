package com.example.PAPS.repositories;

import com.example.PAPS.entities.SupplementsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplementReportRepository extends JpaRepository<SupplementsReport, Long> {
    List<SupplementsReport> findAllByMonth(String month);
}
