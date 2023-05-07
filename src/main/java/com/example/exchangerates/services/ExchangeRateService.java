package com.example.exchangerates.services;

import com.example.exchangerates.entitys.ExchangeRate;
import com.example.exchangerates.models.ExchangeRateDTO;
import com.example.exchangerates.repository.ExchangeRateRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {
    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    public List<ExchangeRateDTO> findAll() {
        return exchangeRateRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<ExchangeRateDTO> findByCurrentDay() {
        return exchangeRateRepository.getAverageExchangeRates()
                .stream()
                .map(this::convertObjToDTO)
                .collect(Collectors.toList());
    }

    public List<ExchangeRateDTO> findByCurrentDay(Date startDate, Date endDate) {
        return exchangeRateRepository.getAverageExchangeRates(startDate, endDate)
                .stream()
                .map(this::convertObjToDTO)
                .collect(Collectors.toList());
    }

    private ExchangeRateDTO convertObjToDTO(@NonNull Object[] entity) {
        return ExchangeRateDTO.builder()
                .rate((Double) entity[1])
                .currencyCode((Integer) entity[0])
                .build();
    }

    private ExchangeRateDTO convertEntityToDTO(@NonNull ExchangeRate entity) {
        return ExchangeRateDTO.builder()
                .rate(entity.getRate())
                .utilDate(entity.getUtilDate())
                .currencyCode(entity.getCurrencyCode())
                .build();
    }

}
