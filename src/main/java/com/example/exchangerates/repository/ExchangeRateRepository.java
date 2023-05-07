package com.example.exchangerates.repository;

import com.example.exchangerates.entitys.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("SELECT currencyCode, AVG(rate) AS avgRate FROM ExchangeRate WHERE utilDate = CURRENT_DATE() GROUP BY utilDate, currencyCode")
    List<Object[]> getAverageExchangeRates();
    @Query("SELECT currencyCode, AVG(rate) AS avgRate FROM ExchangeRate WHERE utilDate BETWEEN :startDate AND :endDate GROUP BY utilDate, currencyCode")
    List<Object[]> getAverageExchangeRates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
