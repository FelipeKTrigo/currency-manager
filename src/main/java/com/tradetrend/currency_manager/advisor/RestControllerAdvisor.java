package com.tradetrend.currency_manager.advisor;

import com.tradetrend.currency_manager.dtos.exception.NotFoundInExchanger;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerAdvisor {

    private static final Logger log = LoggerFactory.getLogger(RestControllerAdvisor.class);

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity resposta404(FeignException ex, WebRequest request){
        var replacement = ex.getMessage().split(":");
        var finale = ex.getMessage().replace(replacement[0]+":"+replacement[1]+":", "");
        log.error(finale);
        return ResponseEntity.ofNullable(new NotFoundInExchanger(finale));
    }
}
