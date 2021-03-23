package com.mercadolibre.iplocalization.controllers;

import com.mercadolibre.iplocalization.domain.usecase.components.DataDistancesWithUserUtilization;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryCash;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryIpData;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryLanguage;
import com.mercadolibre.iplocalization.domain.usecase.dto.InfoDataCountry;
import com.mercadolibre.iplocalization.domain.usecase.responses.CountryDistance;
import com.mercadolibre.iplocalization.infrastructure.delivery.rest.StatisticsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticsControllerTest {


    @Autowired
    private StatisticsController statisticsController;

    @Test
    public void getFarthestDistanceFromBsAs() {
        ResponseEntity<CountryDistance> countryDistanceResponseEntity = statisticsController.getFarthestDistanceFromBsAs();
        assertEquals(countryDistanceResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getClosestDistanceFromBsAs() {
        ResponseEntity<CountryDistance> countryDistanceResponseEntityCloses = statisticsController.getClosestDistanceFromBsAs();
        assertEquals(countryDistanceResponseEntityCloses.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getTotalAverage() {
        ResponseEntity<Long> longResponseEntity = statisticsController.getTotalAverage();
        assertEquals(longResponseEntity.getStatusCode(), HttpStatus.OK);
    }

/*    @Test
    public void preparedataDistancesWithUserUtilization() {
        CountryIpData countryIpData = prepareCountryIpData();
        dataDistancesWithUserUtilization.populate(countryIpData,prepareInfoDataCountry());
        CountryDistance countryDistance = concurrentHashMap.get(countryIpData.getCountryCode());
        concurrentHashMap.put(countryIpData.getCountryCode(), countryDistance);
        countryDistance.getInvocations().incrementAndGet();
    }*/


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
        timezones.add("UTC-12:00");

        LinkedList<Integer> latlng = new LinkedList<>();
        latlng.add(38);
        latlng.add(-97);

        CountryCash cashs = new CountryCash();
        cashs.setCode("USD");
        cashs.setSymbol("$");
        cashs.setName("United States dollar");

        LinkedList<CountryCash> cashsLinkedList = new LinkedList<>();
        cashsLinkedList.add(cashs);

        CountryLanguage languages = new CountryLanguage();
        languages.setName("English");
        languages.setIso639_1("en");

        LinkedList<CountryLanguage> languagesLinkedList = new LinkedList<>();
        languagesLinkedList.add(languages);

        infoDataCountry.setTimezones(timezones);
        infoDataCountry.setCurrencies(cashsLinkedList);
        infoDataCountry.setLanguages(languagesLinkedList);
        infoDataCountry.setLatlng(latlng);

        return infoDataCountry;
    }
}
