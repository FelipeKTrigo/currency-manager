package com.tradetrend.currency_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = "simulation")
public class Simulation {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private LocalDateTime simulationDateTime;
    private BigDecimal totalAmountExchange;
    private String originCurrency;
    private String destinyCurrency;
    private BigDecimal originCurrencyValue;
    private BigDecimal destinyCurrencyValue;
    private BigDecimal monetaryTargetQuantity;
    private BigDecimal monetarySourceQuantity;
}
