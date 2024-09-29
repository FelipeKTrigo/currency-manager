package com.tradetrend.currency_manager.mapper;

import com.tradetrend.currency_manager.dtos.SimulationRequestDTO;
import com.tradetrend.currency_manager.model.Simulation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class SimulationMapper {

    public static void saveSimulationMapper(Simulation simulation, Double currencyTarget, SimulationRequestDTO requestDTO){
        simulation.setSimulationDateTime(LocalDateTime.now());
        simulation.setOriginCurrency(requestDTO.getOriginCurrency());
        simulation.setDestinyCurrency(requestDTO.getDestinyCurrency());
        simulation.setOriginCurrencyValue(BigDecimal.valueOf(1));
        simulation.setDestinyCurrencyValue(BigDecimal.valueOf(currencyTarget));
        simulation.setMonetarySourceQuantity(requestDTO.getMonetarySourceQuantity());
        simulation.setMonetaryTargetQuantity(requestDTO.getMonetaryTargetQuantity());
    }
}
