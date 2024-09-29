package com.tradetrend.currency_manager.service;

import com.tradetrend.currency_manager.dtos.CurrencyGetResponse;
import com.tradetrend.currency_manager.dtos.SimulationRequestDTO;
import com.tradetrend.currency_manager.mapper.SimulationMapper;
import com.tradetrend.currency_manager.model.Simulation;
import com.tradetrend.currency_manager.properties.ApplicationProperties;
import com.tradetrend.currency_manager.repository.SimulationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class SimulationsService {

    @Autowired
    private SimulationRepository repository;

    public Simulation generateSimulationAndSave(SimulationRequestDTO request, CurrencyGetResponse currencyObject, ApplicationProperties properties) {
        log.info("entradas na service - request: {}, currency object: {}, properties: {}", request, currencyObject, properties);
        Simulation simulation = new Simulation();
        if (request.getMonetarySourceQuantity() != null) {
            simulation.setTotalAmountExchange(request.getMonetarySourceQuantity().multiply(BigDecimal.valueOf(Double.parseDouble(currencyObject.getHigh()))));
            simulation.setTotalAmountExchange(calculateCommission(simulation, properties));
        } else {
            simulation
                    .setTotalAmountExchange(request
                            .getMonetaryTargetQuantity()
                            .divide(BigDecimal.valueOf(Double.parseDouble(currencyObject.getHigh())), 3, RoundingMode.HALF_UP));
            simulation.setTotalAmountExchange(calculateCommission(simulation, properties));
        }
        SimulationMapper.saveSimulationMapper(simulation, Double.parseDouble(currencyObject.getHigh()), request);
        log.info("objeto sendo salvo no banco de dados:{}", simulation);
        return repository.save(simulation);
    }

    private BigDecimal calculateCommission(Simulation simulation, ApplicationProperties properties) {
        return simulation
                .getTotalAmountExchange()
                .add(simulation
                        .getTotalAmountExchange()
                        .multiply(BigDecimal.valueOf(properties.getProfitPercentage()))
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                );
    }
}
