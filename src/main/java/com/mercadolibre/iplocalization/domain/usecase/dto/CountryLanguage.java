package com.mercadolibre.iplocalization.domain.usecase.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryLanguage {

	private String iso639_1;
	private String name;
}
