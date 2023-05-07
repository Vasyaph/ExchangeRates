package com.example.exchangerates.services;

import com.example.exchangerates.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyUpdateService {
    @Autowired
    ExchangeRateRepository courseRepository;
    @Autowired
    ExchangeRatesFromApiService apiService;
    public void update(){
        courseRepository.saveAll(apiService.findAllFromMono());
        courseRepository.saveAll(apiService.findAllFromNational());
        courseRepository.saveAll(apiService.findAllFromPrivatebank());
    }
}
