package com.example.exchangerates.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonoExchangeRateDTO {
    int currencyCodeA;
    int currencyCodeB;
    int date;
    double rateBuy;
    double rateCross;
    double rateSell;
}
