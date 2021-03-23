package com.mercadolibre.iplocalization.domain.usecase.components;

import com.mercadolibre.iplocalization.domain.usecase.dto.InfoDataCountry;
import com.mercadolibre.iplocalization.domain.usecase.dto.CountryIpData;
import com.mercadolibre.iplocalization.domain.usecase.responses.CountryDistance;
import com.mercadolibre.iplocalization.infrastructure.util.BuilderStringResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataDistancesWithUserUtilization {

    private final ConcurrentHashMap<String, CountryDistance> concurrentHashMap;

    @Autowired
    private BuilderStringResponses builderStringResponses;

    public DataDistancesWithUserUtilization() {
        this.concurrentHashMap = new ConcurrentHashMap<>();
    }


    public void populate(CountryIpData countryByIp, InfoDataCountry infoByCountryCode) {
        CountryDistance countryDistance = concurrentHashMap.get(countryByIp.getCountryCode());
        if (countryDistance == null) {
            countryDistance = new CountryDistance(countryByIp.getCountryName(), builderStringResponses.getDistance(infoByCountryCode.getLatlng()));
            concurrentHashMap.put(countryByIp.getCountryCode(), countryDistance);
        } else {
            countryDistance.getInvocations().incrementAndGet();
        }
    }

    public CountryDistance getFarthestDistanceFromBsAs() {
        Optional<Entry<String, CountryDistance>> max = concurrentHashMap.entrySet()
                .stream().max(Entry.comparingByValue(Comparator.comparing(CountryDistance::getDistanceInKm)));
        if (max.isPresent()) {
            return max.get().getValue();
        }
        return null;

    }

    public CountryDistance getClosestDistanceFromBsAs() {
        Optional<Entry<String, CountryDistance>> min = concurrentHashMap.entrySet()
                .stream().min(Entry.comparingByValue(Comparator.comparing(CountryDistance::getDistanceInKm)));
        if (min.isPresent()) {
            return min.get().getValue();
        }
        return null;
    }

    public long getTotalAverage() {
        long totalAverage = 0;
        for (Entry<String, CountryDistance> entry : concurrentHashMap.entrySet()) {
            CountryDistance value = entry.getValue();
            totalAverage = value.getDistanceInKm() * value.getInvocations().get();
        }
        return totalAverage;
    }
}
