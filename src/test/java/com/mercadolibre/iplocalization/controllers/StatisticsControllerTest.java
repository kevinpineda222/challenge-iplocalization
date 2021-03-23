package com.mercadolibre.iplocalization.controllers;

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
}
