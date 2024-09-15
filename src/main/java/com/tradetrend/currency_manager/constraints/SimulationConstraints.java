package com.tradetrend.currency_manager.constraints;

import com.tradetrend.currency_manager.dtos.SimulationRequestDTO;
import com.tradetrend.currency_manager.dtos.exception.SimulationConstraintException;
import com.tradetrend.currency_manager.properties.ApplicationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimulationConstraints {

    public static void validateRequestSimulationEntry(SimulationRequestDTO request, ApplicationProperties properties) throws SimulationConstraintException {
        if (request.getDestinyCurrency()==null&&request.getOriginCurrency()==null){
            throw new SimulationConstraintException("must have origin currency or destiny currency");
        }
        if(request.getProposalSimulationDateTime().plusMinutes(properties.getValidProposalTime()).isBefore(LocalDateTime.now())){
            throw new SimulationConstraintException("proposal simulation past simulation range");
        }
    }
}
