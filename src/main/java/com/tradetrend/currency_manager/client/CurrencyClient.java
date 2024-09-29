package com.tradetrend.currency_manager.client;

import com.tradetrend.currency_manager.dtos.CurrencyGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "currency-client",url = "${endpoints.baseCurrency}")
public interface CurrencyClient {
    @GetMapping("${endpoints.currencies}{currencies}")
    ResponseEntity<Map<String,CurrencyGetResponse>> queryCurrencies(@PathVariable("currencies")String currencies);

}
