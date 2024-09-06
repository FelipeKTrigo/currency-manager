package com.tradetrend.currency_manager.controller;

import com.google.gson.Gson;
import com.tradetrend.currency_manager.dtos.CurrenciesDTO;
import com.tradetrend.currency_manager.service.CurrencyService;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CurrencyController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);
    @Autowired
    private CurrencyService service;

    @GetMapping("/currencies")
    public ResponseEntity getCurrencies(@RequestParam @NotNull(message = "originCurrency can't be null") String originCurrency,
                                        @RequestParam @NotNull(message = "destinyCurrency can't be null") String destinyCurrency){
        log.info("Request:{},{}",originCurrency,destinyCurrency);
        Object response = service.getCurrencies(originCurrency,destinyCurrency);
        log.info("Response:{}",response);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/currencies/list")
    public ResponseEntity<List<CurrenciesDTO>> listCurrenciesAvailable(){
        log.info("Request list active currencies");
        List<CurrenciesDTO> list = service.listAllCurrenciesAvaliable();
        log.info("Response:{}",new Gson().toJson(list));
        return ResponseEntity.ok(list);
    }
}
