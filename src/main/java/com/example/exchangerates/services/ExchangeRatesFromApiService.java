package com.example.exchangerates.services;

import com.example.exchangerates.entitys.ExchangeRate;
import com.example.exchangerates.models.MonoExchangeRateDTO;
import com.example.exchangerates.models.NationalExchangeRateDTO;
import com.example.exchangerates.models.PrivatebankExchangeRateDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRatesFromApiService {
    @Autowired
    private ExchangeApiClient jobExchangeClient;

    public List<ExchangeRate> findAllFromPrivatebank() {
        return jobExchangeClient.getPrivateExchangeRates().stream()
                .map(this::toExchangeRate)
                .collect(Collectors.toList());
    }

    public List<ExchangeRate> findAllFromMono() {
        return jobExchangeClient.getMonoExchangeRates().stream()
                .filter(a->a.getCurrencyCodeB()==980)//filter only exchange rates in UAH
                .map(this::toExchangeRate)
                .collect(Collectors.toList());
    }

    public List<ExchangeRate> findAllFromNational() {
        return jobExchangeClient.getNationalExchangeRates().stream()
                .map(this::toExchangeRate)
                .collect(Collectors.toList());
    }

    private ExchangeRate toExchangeRate(@NonNull PrivatebankExchangeRateDTO input) {
        ExchangeRate out = new ExchangeRate();
        out.setCurrencyCode(input.getCcy().equals("EUR") ? 978 : 840);
        out.setRate((Double.valueOf(input.getBuy()) + Double.valueOf(input.getSale())) / 2);
        out.setUtilDate(new Date());
        out.setBankName("PrivateBank");

        return out;
    }

    private ExchangeRate toExchangeRate(@NonNull NationalExchangeRateDTO input) {
        ExchangeRate out = new ExchangeRate();
        out.setCurrencyCode(input.getR030());
        out.setRate(input.getRate());
        out.setUtilDate(new Date());
        out.setBankName("NationalBank");
        return out;
    }

    private ExchangeRate toExchangeRate(@NonNull MonoExchangeRateDTO input) {
        ExchangeRate out = new ExchangeRate();
        out.setCurrencyCode(input.getCurrencyCodeA());
        if (input.getRateCross() > 0)
            out.setRate(input.getRateCross());
        else
            out.setRate((input.getRateSell() + input.getRateBuy()) / 2);
        out.setUtilDate(new Date());
        out.setBankName("MonoBank");

        return out;
    }
}
