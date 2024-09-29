package com.tradetrend.currency_manager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal monetaryTargetQuantity;
    private BigDecimal monetarySourceQuantity;

}
