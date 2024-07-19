package com.tradetrend.currency_manager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-client",url = "${endpoints.baseCurrency}")
public interface CurrencyClient {
    @GetMapping("${endpoints.currencies}{currencies}")
    ResponseEntity<Object> queryCurrencies(@PathVariable("currencies")String currencies);

}
