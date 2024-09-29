package com.tradetrend.currency_manager.constraints;

import com.tradetrend.currency_manager.dtos.SimulationRequestDTO;
import com.tradetrend.currency_manager.dtos.exception.SimulationConstraintException;
import org.springframework.stereotype.Component;

@Component
public class SimulationConstraints {

    public static void validateRequestSimulationEntry(SimulationRequestDTO request) throws SimulationConstraintException {
        if (request.getDestinyCurrency() == null && request.getOriginCurrency() == null) {
            throw new SimulationConstraintException("must have origin currency or destiny currency");
        }
        if (request.getMonetaryTargetQuantity() != null && request.getMonetarySourceQuantity() != null) {
            throw new SimulationConstraintException("must have defined source currency or target currency for exchange");
        }
    }
}
