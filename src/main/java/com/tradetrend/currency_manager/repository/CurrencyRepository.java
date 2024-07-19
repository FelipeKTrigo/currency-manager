package com.tradetrend.currency_manager.repository;

import com.tradetrend.currency_manager.model.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currencies,Integer>{}