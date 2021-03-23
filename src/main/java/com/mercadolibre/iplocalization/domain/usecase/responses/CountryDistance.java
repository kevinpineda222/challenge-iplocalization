package com.mercadolibre.iplocalization.domain.usecase.responses;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class CountryDistance {

	private String countryDistanceTested;
	private long distanceInKm;
	private AtomicLong invocations; 
	
	public CountryDistance(String countryDistanceTested, long distanceInKm) {
		this.countryDistanceTested = countryDistanceTested;
		this.distanceInKm = distanceInKm;
		this.invocations = new AtomicLong(1);
	}
}
