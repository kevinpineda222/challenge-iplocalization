package com.mercadolibre.iplocalization.infrastructure.delivery.rest;

import com.mercadolibre.iplocalization.domain.usecase.components.DataDistancesWithUserUtilization;
import com.mercadolibre.iplocalization.domain.usecase.responses.CountryDistance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Estadísticas de Distancias"})
@RestController
@RequestMapping("/stadistics")
public class StatisticsController {

    @Autowired
    private DataDistancesWithUserUtilization dataDistancesWithUserUtilization;

    @ApiOperation(value = "Distancia más lejana desde Buenos Aires")
    @GetMapping("/distance/farthest")
    public ResponseEntity<CountryDistance> getFarthestDistanceFromBsAs() {
        return new ResponseEntity<>(dataDistancesWithUserUtilization.getFarthestDistanceFromBsAs(), HttpStatus.OK);
    }

    @ApiOperation(value = "Distancia más cercana desde Buenos Aires")
    @GetMapping("/distance/closest")
    public ResponseEntity<CountryDistance> getClosestDistanceFromBsAs() {
        return new ResponseEntity<>(dataDistancesWithUserUtilization.getClosestDistanceFromBsAs(), HttpStatus.OK);
    }

    @ApiOperation(value = "Promedio de todas las Distancias")
    @GetMapping("/distance/average")
    public ResponseEntity<Long> getTotalAverage() {
        return new ResponseEntity<>(dataDistancesWithUserUtilization.getTotalAverage(), HttpStatus.OK);
    }

}
