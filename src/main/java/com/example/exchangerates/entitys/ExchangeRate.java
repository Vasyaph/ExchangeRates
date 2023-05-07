package com.example.exchangerates.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;
    String bankName;
    int currencyCode;
    Double rate;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date utilDate;

}
