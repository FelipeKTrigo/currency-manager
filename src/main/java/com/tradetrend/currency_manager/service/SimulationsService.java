package com.tradetrend.currency_manager.service;

import com.tradetrend.currency_manager.dtos.SimulationRequestDTO;
import com.tradetrend.currency_manager.mapper.SimulationMapper;
import com.tradetrend.currency_manager.model.Simulation;
import com.tradetrend.currency_manager.properties.ApplicationProperties;
import com.tradetrend.currency_manager.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimulationsService {

    @Autowired
    private SimulationRepository repository;

    public Simulation generateSimulationAndSave(SimulationRequestDTO request, ApplicationProperties properties) {
        Simulation simulation = new Simulation();
        if(request.getMonetarySourceQuantity()!=null){
            simulation.setTotalAmountExchange(request.getMonetarySourceQuantity().multiply(request.getDestinyCurrencyValue()));
            simulation.setTotalAmountExchange(calculateCommission(simulation,properties));
        }else{
            simulation
                    .setTotalAmountExchange(request
                    .getMonetaryTargetQuantity()
                    .divide(request.getDestinyCurrencyValue(),3, RoundingMode.HALF_UP));
            simulation.setTotalAmountExchange(calculateCommission(simulation,properties));
        }
        simulation = SimulationMapper.saveSimulationMapper(simulation,request);
        repository.save(simulation);
        return simulation;
    }

    private BigDecimal calculateCommission(Simulation simulation,ApplicationProperties properties){
        return simulation
                .getTotalAmountExchange()
                .add(simulation
                        .getTotalAmountExchange()
                        .multiply(BigDecimal.valueOf(properties.getProfitPercentage()))
                        .divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)
                );
    }
}
