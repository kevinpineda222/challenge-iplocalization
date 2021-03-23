package com.mercadolibre.iplocalization.domain.usecase.dto;

import lombok.Data;


@Data
public class CountryIpData {

	private String countryCode;
	private String countryCode3;
	private String countryName;
}
