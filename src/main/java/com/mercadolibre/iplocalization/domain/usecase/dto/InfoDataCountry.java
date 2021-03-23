package com.mercadolibre.iplocalization.domain.usecase.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;

@Data
public class InfoDataCountry implements Serializable {

    private static final long serialVersionUID = 1L;

    private LinkedList<CountryLanguage> languages;
    private LinkedList<String> timezones;
    private LinkedList<CountryCash> currencies;
    private LinkedList<Integer> latlng;

}
