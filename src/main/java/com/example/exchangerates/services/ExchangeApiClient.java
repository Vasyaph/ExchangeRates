package com.example.exchangerates.services;


import com.example.exchangerates.models.MonoExchangeRateDTO;
import com.example.exchangerates.models.NationalExchangeRateDTO;
import com.example.exchangerates.models.PrivatebankExchangeRateDTO;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Component
public class ExchangeApiClient {
    HttpClient httpClient = HttpClientBuilder.create().build();
    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(requestFactory);

    public List<MonoExchangeRateDTO> getMonoExchangeRates() {
        String url = "https://api.monobank.ua/bank/currency";
        try {
            MonoExchangeRateDTO[] response = restTemplate.getForObject(new URI(url), MonoExchangeRateDTO[].class);
            return Arrays.asList(response);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PrivatebankExchangeRateDTO> getPrivateExchangeRates() {
        String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
        try {
            PrivatebankExchangeRateDTO[] response = restTemplate.getForObject(new URI(url), PrivatebankExchangeRateDTO[].class);
            return Arrays.asList(response);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NationalExchangeRateDTO> getNationalExchangeRates() {
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        try {
            NationalExchangeRateDTO[] response = restTemplate.getForObject(new URI(url), NationalExchangeRateDTO[].class);
            return Arrays.asList(response);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
