package com.mercadolibre.iplocalization.domain.usecase.country;

import com.mercadolibre.iplocalization.domain.usecase.components.DataDistancesWithUserUtilization;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryCash;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryExchange;
import com.mercadolibre.iplocalization.domain.usecase.dto.InfoDataCountry;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryIpData;
import com.mercadolibre.iplocalization.domain.usecase.responses.LocalizationDataIP;
import com.mercadolibre.iplocalization.infrastructure.util.BuilderStringResponses;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;


@Service
public class CountryInfoSearched {

    @Value("${app.currency.symbol}")
    private String currencySymbol;

    @Autowired
    private CountryApisService countryApisService;

    @Autowired
    private DataDistancesWithUserUtilization dataDistancesWithUserUtilization;

    @Autowired
    private BuilderStringResponses builderStringResponses;

    private static final Logger logger = LoggerFactory.getLogger(CountryInfoSearched.class);

    @Cacheable("IP_SEARCH")
    public LocalizationDataIP getInfoByIp(String ip) {
        LocalizationDataIP localizationDataIP = new LocalizationDataIP();

        CountryIpData countryByIp = countryApisService.getCountryByIp(ip);
        InfoDataCountry infoByCountryCode = countryApisService.getInfoByCountryCode(countryByIp.getCountryCode());

        localizationDataIP.setIp(ip);
        localizationDataIP.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        localizationDataIP.setCountry(countryByIp.getCountryName());
        localizationDataIP.setIsoCode(countryByIp.getCountryCode());
        localizationDataIP.setCurrency(cashFormater(infoByCountryCode.getCurrencies()));

        localizationDataIP.setTime(builderStringResponses.getFormattedTime(infoByCountryCode.getTimezones()));
        localizationDataIP.setLanguage(builderStringResponses.getFormattedLanguages(infoByCountryCode.getLanguages()));
        localizationDataIP.setDistance(builderStringResponses.getFormattedDistance(infoByCountryCode.getLatlng()));

        dataDistancesWithUserUtilization.populate(countryByIp, infoByCountryCode);
        return localizationDataIP;
    }

    public String cashFormater(LinkedList<CountryCash> currencies) {
        StringBuilder builder = new StringBuilder();
        for (Iterator<CountryCash> iterator = currencies.iterator();
             iterator.hasNext(); ) {

            CountryCash countryCash = iterator.next();
            CountryExchange exchange;
            try {
                exchange = countryApisService.getCountryCash(countryCash.getCode(), currencySymbol);
                builder.append(countryCash.getCode()).append(" (1 ").append(countryCash.getCode())
                        .append(" = ").append(exchange.getRates().get(currencySymbol))
                        .append(" ").append(currencySymbol).append(")");
            } catch (HttpStatusCodeException ex) {
                String error = new JSONObject(ex.getResponseBodyAsString()).getString("error");
                builder.append(countryCash.getCode()).append(" (").append(error).append(")");
            }

            if (iterator.hasNext()) {
                builder.append(", ");
            }

        }
        return builder.toString();
    }
}
