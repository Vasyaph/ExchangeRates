package com.example.exchangerates.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDTO {
    int currencyCode;
    Double rate;
    private java.util.Date utilDate;
}
