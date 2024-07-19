package com.tradetrend.currency_manager.mapper;

import com.tradetrend.currency_manager.dtos.CurrenciesDTO;
import com.tradetrend.currency_manager.model.Currencies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyMapper {

    public CurrenciesDTO toCurrenciesDTO(Currencies currencies) {
        return new CurrenciesDTO(currencies.getName());
    }

    public List<CurrenciesDTO> toCurrenciesDTOList(List<Currencies> currenciesList) {
        return currenciesList.stream().map(this::toCurrenciesDTO).toList();
    }
}
