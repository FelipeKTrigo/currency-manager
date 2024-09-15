package com.tradetrend.currency_manager.repository;

import com.tradetrend.currency_manager.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation,Long> {
}
