package com.example.exchangerates.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NationalExchangeRateDTO {
    int r030;
    String txt;
    double rate;
    String cc;
    String exchangedate;
}
