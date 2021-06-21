package com.example.PAPS.repositories;

import com.example.PAPS.entities.MaintenanceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceOrderRepository extends JpaRepository<MaintenanceOrder, Long> {
    List<MaintenanceOrder> findAllByIsDoneIsFalse();

    List<MaintenanceOrder> findAll();

    Optional<MaintenanceOrder> findById(Long id);

    List<MaintenanceOrder> findAllByIsDoneIsTrue();
}
