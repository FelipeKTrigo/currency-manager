package com.tradetrend.currency_manager.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationProperties {
    @Value("${parameters.validProposalRange}")
    private Integer validProposalTime;
    @Value("${parameters.exchangeProfitPercentage}")
    private Integer profitPercentage;
}
