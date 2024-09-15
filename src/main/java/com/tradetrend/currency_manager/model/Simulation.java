package com.tradetrend.currency_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "simulation")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime simulationDateTime;
    private BigDecimal totalAmountExchange;
    private String originCurrency;
    private String destinyCurrency;
    private BigDecimal originCurrencyValue;
    private BigDecimal destinyCurrencyValue;
    private BigDecimal monetaryTargetQuantity;
    private BigDecimal monetarySourceQuantity;
}
