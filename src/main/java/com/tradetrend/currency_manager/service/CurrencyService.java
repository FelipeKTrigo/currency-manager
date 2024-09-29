package com.tradetrend.currency_manager.service;

import com.tradetrend.currency_manager.client.CurrencyClient;
import com.tradetrend.currency_manager.dtos.CurrenciesDTO;
import com.tradetrend.currency_manager.dtos.CurrencyGetResponse;
import com.tradetrend.currency_manager.mapper.CurrencyMapper;
import com.tradetrend.currency_manager.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {
    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    @Autowired
    private CurrencyClient client;
    @Autowired
    private CurrencyRepository repository;
    @Autowired
    private CurrencyMapper mapper;

    public Map<String, CurrencyGetResponse> getCurrencies(String origin, String destiny){
        String currencies = String.format("%s-%s",origin,destiny);
        log.info("request coin api with selected currencies: {}",currencies);
        var response = client.queryCurrencies(currencies);
        log.info("response coin api: {}",response.getBody());
        return response.getBody();
    }
    public List<CurrenciesDTO> listAllCurrenciesAvaliable(){
        return mapper.toCurrenciesDTOList(repository
                .findAll()
                .stream()
                .filter(e->e.getActive().equals(Boolean.TRUE))
                .toList());
    }

}
