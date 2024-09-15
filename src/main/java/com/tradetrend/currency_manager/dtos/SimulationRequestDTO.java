package com.tradetrend.currency_manager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SimulationRequestDTO {
    @NotNull(message = "originCurrency cannot be null")
    @NotBlank(message = "originCurrency cannot be blank")
    private String originCurrency;
    @NotNull(message = "destinyCurrency cannot be null")
    @NotBlank(message = "destinyCurrency cannot be blank")
    private String destinyCurrency;
    @NotNull(message = "originCurrencyValue cannot be null")
    private BigDecimal originCurrencyValue;
    @NotNull(message = "destinyCurrencyValue cannot be null")
    private BigDecimal destinyCurrencyValue;
    @NotNull(message = "proposalSimulationDateTime cannot be null")
    @Past(message = "proposalSimulationDateTime cannot be on future")
    private LocalDateTime proposalSimulationDateTime;

    private BigDecimal monetaryTargetQuantity;
    private BigDecimal monetarySourceQuantity;

}
