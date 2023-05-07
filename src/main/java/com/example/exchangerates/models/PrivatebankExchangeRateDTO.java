package com.example.exchangerates.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivatebankExchangeRateDTO {

    String ccy;
    String base_ccy;
    String buy;
    String sale;
}
