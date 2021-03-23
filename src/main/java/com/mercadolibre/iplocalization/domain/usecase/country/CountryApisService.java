package com.mercadolibre.iplocalization.domain.usecase.country;

import com.mercadolibre.iplocalization.domain.usecase.dto.CountryExchange;
import com.mercadolibre.iplocalization.domain.usecase.dto.InfoDataCountry;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryIpData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryApisService {

    @Value("${app.external.api.ip2country.url}")
    private String urlDataIpCountry;
    @Value("${app.external.api.restcountries.url}")
    private String urlDataRestCountries;
    @Value("${app.external.api.exchangeratesapi.url}")
    private String exchangeRatesApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    private static final String LOGGERMESSAGE = "Current Response of the Service ----> {} ";

    private static final Logger logger = LoggerFactory.getLogger(CountryApisService.class);

    public CountryIpData getCountryByIp(String ip) {

        CountryIpData countryIpData;
        String url = urlDataIpCountry + "?" + ip;
        countryIpData = restTemplate.getForObject(url, CountryIpData.class);

        logger.info(LOGGERMESSAGE,countryIpData);
        return countryIpData;
    }

    public InfoDataCountry getInfoByCountryCode(String countryCode) {

        InfoDataCountry infoDataCountry;
        String url = urlDataRestCountries + "/" + countryCode;
        infoDataCountry = restTemplate.getForObject(url, InfoDataCountry.class);

        logger.info(LOGGERMESSAGE,infoDataCountry);
        return infoDataCountry;
    }

    public CountryExchange getCountryCash(String baseCurrency, String currentCurrency) {

        CountryExchange countryExchange;
        String url = exchangeRatesApiUrl + "?symbols=" + currentCurrency + "&base=" + baseCurrency;
        countryExchange = restTemplate.getForObject(url, CountryExchange.class);

        logger.info(LOGGERMESSAGE,countryExchange);
        return countryExchange;
    }

}
