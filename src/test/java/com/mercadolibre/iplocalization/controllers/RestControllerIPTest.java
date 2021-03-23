package com.mercadolibre.iplocalization.controllers;

import com.mercadolibre.iplocalization.domain.usecase.country.CountryInfoSearched;
import com.mercadolibre.iplocalization.domain.usecase.responses.LocalizationDataIP;
import com.mercadolibre.iplocalization.infrastructure.delivery.rest.RestControllerIP;
import com.mercadolibre.iplocalization.infrastructure.exceptions.BadIpFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RestControllerIPTest {

    @Autowired
    public RestControllerIP restControllerIP;

    @Autowired
    public CountryInfoSearched countryInfoSearched;

    private String ipDefaultTest = "181.229.54.217";

    private String ipDefaultInvalidFormat = "181.2290.2020.1100";


    @Test(expected = BadIpFormatException.class)
    public void badRequestTest() throws BadIpFormatException {
        ResponseEntity<LocalizationDataIP> responseEntity = restControllerIP.getCountryInfoByIp(ipDefaultInvalidFormat);
    }
    @Test
    public void getCountryInfoByIp() throws BadIpFormatException {

        ResponseEntity<LocalizationDataIP> dataIPResponseEntity = restControllerIP.getCountryInfoByIp(ipDefaultTest);
        LocalizationDataIP localizationDataIP = countryInfoSearched.getInfoByIp(ipDefaultTest);

        assertEquals(dataIPResponseEntity.getStatusCode(), HttpStatus.OK);

        assertNotNull(localizationDataIP);
        assertEquals(localizationDataIP.getCountry(), prepareLocalizationDataIP().getCountry());
        assertEquals(localizationDataIP.getDistance(), prepareLocalizationDataIP().getDistance());
        assertEquals(localizationDataIP.getIsoCode(), prepareLocalizationDataIP().getIsoCode());
        assertEquals(localizationDataIP.getLanguage(), prepareLocalizationDataIP().getLanguage());
    }

    public LocalizationDataIP prepareLocalizationDataIP() {
        LocalizationDataIP localizationDataIP = new LocalizationDataIP();
        localizationDataIP.setCountry("Argentina");
        localizationDataIP.setLocalDateTime("23-03-2021 01:08:17");
        localizationDataIP.setCurrency("ARS (Base 'ARS' is not supported.)");
        localizationDataIP.setDistance("0 Kilometres {-34, -64} -- a -- {-34, -64}");
        localizationDataIP.setLanguage("Spanish {es}, Guaraní {gn}");
        localizationDataIP.setIsoCode("AR");
        localizationDataIP.setTime("22:08:17 [UTC-03:00]");

        return localizationDataIP;
    }
}
