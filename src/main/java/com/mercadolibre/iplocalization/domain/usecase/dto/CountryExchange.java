package com.mercadolibre.iplocalization.domain.usecase.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class CountryExchange implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Double> rates;
}
