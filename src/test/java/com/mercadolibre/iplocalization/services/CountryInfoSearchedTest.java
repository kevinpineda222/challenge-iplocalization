package com.mercadolibre.iplocalization.services;

import com.mercadolibre.iplocalization.domain.usecase.components.DataDistancesWithUserUtilization;
import com.mercadolibre.iplocalization.domain.usecase.responses.LocalizationDataIP;
import com.mercadolibre.iplocalization.infrastructure.util.BuilderStringResponses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import com.mercadolibre.iplocalization.domain.usecase.country.*;
import com.mercadolibre.iplocalization.domain.usecase.dto.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


import java.util.LinkedList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryInfoSearchedTest {

    @Autowired
    CountryInfoSearched countryInfoSearched;

    @Autowired
    CountryApisService countryApisService;

    private static final String IP_TEST = "66.200.30.230";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtenerInfoPaisPorIp(){

        LocalizationDataIP localizationDataIP = countryInfoSearched.getInfoByIp(IP_TEST);

        assertNotNull(localizationDataIP);
        assertEquals(localizationDataIP.getCountry(),prepareLocalizationDataIP().getCountry());
        assertEquals(localizationDataIP.getDistance(),prepareLocalizationDataIP().getDistance());
        assertEquals(localizationDataIP.getIsoCode(),prepareLocalizationDataIP().getIsoCode());
        assertEquals(localizationDataIP.getLanguage(),prepareLocalizationDataIP().getLanguage());

    }

    @Test
    public void getfotmatcash() {
        String formatedCash;
        formatedCash = countryInfoSearched.cashFormater(prepareInfoDataCountry().getCurrencies());
        assertNotNull(formatedCash);
    }

    public CountryIpData prepareCountryIpData() {
        CountryIpData countryIpData = new CountryIpData();
        countryIpData.setCountryCode("AR");
        countryIpData.setCountryCode3("ARG");
        countryIpData.setCountryName("Argentina");
        return countryIpData;
    }

    public InfoDataCountry prepareInfoDataCountry() {
        InfoDataCountry infoDataCountry = new InfoDataCountry();
        LinkedList<String> timezones = new LinkedList<>();
        timezones.add("UTC-03:00");

        LinkedList<Integer> latlng = new LinkedList<>();
        latlng.add(-34);
        latlng.add(-64);

        CountryCash cashs = new CountryCash();
        cashs.setCode("ARS");
        cashs.setSymbol("$");
        cashs.setName("Argentine peso");

        LinkedList<CountryCash> cashsLinkedList = new LinkedList<>();
        cashsLinkedList.add(cashs);

        CountryLanguage languages = new CountryLanguage();
        languages.setName("Spanish");
        languages.setIso639_1("es");

        CountryLanguage languages2 = new CountryLanguage();
        languages2.setName("Guaraní");
        languages2.setIso639_1("gn");

        LinkedList<CountryLanguage> languagesLinkedList = new LinkedList<>();
        languagesLinkedList.add(languages);
        languagesLinkedList.add(languages2);


        infoDataCountry.setTimezones(timezones);
        infoDataCountry.setCurrencies(cashsLinkedList);
        infoDataCountry.setLanguages(languagesLinkedList);
        infoDataCountry.setLatlng(latlng);

        return infoDataCountry;
    }

    public CountryCash prepareCountryCash() {

        CountryCash countryCash = new CountryCash();

        countryCash.setCode("AUD");
        countryCash.setName("Australian dollar");
        countryCash.setSymbol("USD");

        return countryCash;

    }

    public LocalizationDataIP prepareLocalizationDataIP(){
        LocalizationDataIP localizationDataIP = new LocalizationDataIP();
        localizationDataIP.setCountry("United States");
        localizationDataIP.setLocalDateTime("22-03-2021 19:49:52");
        localizationDataIP.setCurrency("USD (1 USD = 1.0 USD)");
        localizationDataIP.setDistance("8701 Kilometres {-34, -64} -- a -- {38, -97}");
        localizationDataIP.setLanguage("English {en}");
        localizationDataIP.setIsoCode("US");
        localizationDataIP.setTime("10:49:52 [UTC-12:00] && 11:49:52 [UTC-11:00] && 12:49:52 [UTC-10:00] && " +
                "13:49:52 [UTC-09:00] && 14:49:52 [UTC-08:00] && 15:49:52 [UTC-07:00] && 16:49:52 " +
                "[UTC-06:00] && 17:49:52 [UTC-05:00] && 18:49:52 [UTC-04:00] && 08:49:52 [UTC+10:00] && " +
                "10:49:52 [UTC+12:00]");
        localizationDataIP.setIp("66.200.30.230");

        return localizationDataIP;
    }
}
