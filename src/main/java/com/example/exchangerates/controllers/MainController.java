package com.example.exchangerates.controllers;

import com.example.exchangerates.models.ExchangeRateDTO;
import com.example.exchangerates.services.ExchangeRateService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/exchangeRates")
public class MainController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping
    public List<ExchangeRateDTO> averageExchangeRates() throws URISyntaxException {
        return exchangeRateService.findByCurrentDay();
    }
    @GetMapping("/range")
    public List<ExchangeRateDTO> averageExchangeRatesByRange(
            @NonNull @Parameter(description  = "Start date", example = "20220101") @Schema(type = "integer", format = "int32") @RequestParam("startDate") @DateTimeFormat(pattern = "yyyyMMdd") Date startDate,
            @NonNull @Parameter(description  = "Start date", example = "20220101") @Schema(type = "integer", format = "int32") @RequestParam("endDate") @DateTimeFormat(pattern = "yyyyMMdd") Date endDate) throws URISyntaxException {
        return exchangeRateService.findByCurrentDay(startDate,endDate);
    }

}
