package com.tradetrend.currency_manager.advisor;

import com.google.gson.Gson;
import com.tradetrend.currency_manager.dtos.exception.JsonObject;
import com.tradetrend.currency_manager.dtos.exception.SimulationConstraintException;
import feign.FeignException;
import jakarta.validation.ValidationException;
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
        return ResponseEntity.ofNullable(new JsonObject(finale));
    }
    @ExceptionHandler(value = SimulationConstraintException.class)
    public ResponseEntity resposta400SimulationConstraint(SimulationConstraintException ex, WebRequest request){
        log.error(ex.getMessage());
        return ResponseEntity.ofNullable(new Gson().toJson(new JsonObject(ex.getMessage())));
    }

}
