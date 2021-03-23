package com.mercadolibre.iplocalization.domain.usecase.responses;

import lombok.Data;

@Data
public class LocalizationDataIP {

	private String ip;
	private String localDateTime;
	private String country;
	private String isoCode;
	private String language;
	private String currency;
	private String time;
	private String distance;
}
