package com.mercadolibre.iplocalization.services;

import com.mercadolibre.iplocalization.domain.usecase.country.CountryApisService;
import com.mercadolibre.iplocalization.domain.usecase.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryInfoServiceTest {

    @Autowired
    CountryApisService countryApisService;

    private String ipDefaultTest="181.229.54.217";

    @Test
    public void obtenerPaisPorIp(){
        CountryIpData countryIpData = new CountryIpData();
        countryIpData = countryApisService.getCountryByIp(ipDefaultTest);
        assertNotNull(countryIpData);
        assertEquals(countryIpData, prepareCountryIpData());
        //assertEquals("Australia", countryIpData.getCountryName());
   }

   @Test
   public void getInfoByCountryCode() {
       InfoDataCountry infoDataCountry;
       CountryIpData countryIpData = prepareCountryIpData();
      // when(countryApisService.getInfoByCountryCode(Mockito.anyString())).thenReturn(prepareInfoDataCountry());
       infoDataCountry = countryApisService.getInfoByCountryCode(countryIpData.getCountryCode());
       assertNotNull(infoDataCountry);
       assertEquals(infoDataCountry,prepareInfoDataCountry());
   }

   @Test
   public void getCountryCash() {
        CountryCash countryCash;
        CountryExchange countryExchange = countryApisService.getCountryCash(prepareCountryCash().getCode(),prepareCountryCash().getSymbol());

        assertNotNull(countryExchange);
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

}
