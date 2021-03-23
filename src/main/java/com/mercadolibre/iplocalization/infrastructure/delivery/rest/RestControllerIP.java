package com.mercadolibre.iplocalization.infrastructure.delivery.rest;

import com.mercadolibre.iplocalization.infrastructure.exceptions.BadIpFormatException;
import com.mercadolibre.iplocalization.domain.usecase.responses.LocalizationDataIP;
import org.apache.commons.validator.routines.InetAddressValidator;
import com.mercadolibre.iplocalization.domain.usecase.country.CountryInfoSearched;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = {"Informacion segun IP del Pais"})
@RestController
public class RestControllerIP {

    @Autowired
    private CountryInfoSearched countryInfoSearched;


    @ApiOperation(value = "Retorna informacion del pais segun la IP ingresada")
    @GetMapping(path = "/getdataCountry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocalizationDataIP> getCountryInfoByIp(@RequestParam(required = true) String ip) throws BadIpFormatException {

        if (!InetAddressValidator.getInstance().isValidInet4Address(ip)) {
            throw new BadIpFormatException(ip);
        }

        LocalizationDataIP localizationDataIP = countryInfoSearched.getInfoByIp(ip);
        return new ResponseEntity<>(localizationDataIP, HttpStatus.OK);
    }

}

