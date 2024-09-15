package com.tradetrend.currency_manager.mapper;

import com.tradetrend.currency_manager.dtos.SimulationRequestDTO;
import com.tradetrend.currency_manager.model.Simulation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimulationMapper {

    public static Simulation saveSimulationMapper(Simulation simulation, SimulationRequestDTO requestDTO){
        simulation.setSimulationDateTime(LocalDateTime.now());
        simulation.setOriginCurrency(requestDTO.getOriginCurrency());
        simulation.setDestinyCurrency(requestDTO.getDestinyCurrency());
        simulation.setOriginCurrencyValue(requestDTO.getOriginCurrencyValue());
        simulation.setDestinyCurrencyValue(requestDTO.getDestinyCurrencyValue());
        simulation.setMonetarySourceQuantity(requestDTO.getMonetarySourceQuantity());
        simulation.setMonetaryTargetQuantity(requestDTO.getMonetaryTargetQuantity());
        return simulation;
    }
}
