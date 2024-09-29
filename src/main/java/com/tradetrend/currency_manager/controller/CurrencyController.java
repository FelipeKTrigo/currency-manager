package com.tradetrend.currency_manager.controller;

import com.google.gson.Gson;
import com.tradetrend.currency_manager.constraints.SimulationConstraints;
import com.tradetrend.currency_manager.dtos.CurrenciesDTO;
import com.tradetrend.currency_manager.dtos.CurrencyGetResponse;
import com.tradetrend.currency_manager.dtos.SimulationRequestDTO;
import com.tradetrend.currency_manager.dtos.exception.SimulationConstraintException;
import com.tradetrend.currency_manager.model.Simulation;
import com.tradetrend.currency_manager.properties.ApplicationProperties;
import com.tradetrend.currency_manager.service.CurrencyService;
import com.tradetrend.currency_manager.service.SimulationsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class CurrencyController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);
    @Autowired
    private CurrencyService service;
    @Autowired
    private SimulationsService simulationService;
    @Autowired
    private ApplicationProperties properties;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/simulations")
    public ResponseEntity<Simulation> simulate(@RequestBody @Valid @NotNull(message = "request body can't be null") SimulationRequestDTO request) throws SimulationConstraintException {
        log.info("simulation request: {}",request.toString());
        SimulationConstraints.validateRequestSimulationEntry(request);
        Map<String, CurrencyGetResponse> response = service.getCurrencies(request.getOriginCurrency(), request.getDestinyCurrency());
        CurrencyGetResponse currencyObject = response.getOrDefault(
                (request.getOriginCurrency()+request.getDestinyCurrency()).toUpperCase(),
                response.get(response.keySet().iterator().next()));
        return ResponseEntity.ok(simulationService.generateSimulationAndSave(request,currencyObject,properties));
    }

    @GetMapping("/currencies/list")
    public ResponseEntity<List<CurrenciesDTO>> listCurrenciesAvailable() {
        log.info("Request list active currencies");
        List<CurrenciesDTO> list = service.listAllCurrenciesAvaliable();
        log.info("Response: {}", new Gson().toJson(list));
        return ResponseEntity.ok(list);
    }
}
