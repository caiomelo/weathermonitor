package com.simple.weathermonitor.service;

import com.simple.weathermonitor.model.CityTemperatureInfo;
import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.model.accuweather.ProviderCurrentTemperature;
import com.simple.weathermonitor.repository.UserObservedCityRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Service
public class UserObservedCityService {

    private final UserObservedCityRepository repository;

    private final CityService cityService;

    public List<UserObservedCity> findAll() {
        return repository.findAll();
    }

    public UserObservedCity createOrUpdate(UserObservedCity userObservedCity) {
        return repository.save(userObservedCity);
    }

    public List<CityTemperatureInfo> getCityObservationsFor(String email) {
        List<UserObservedCity> observedCities = repository.getActiveFor(email);

        if (observedCities == null || observedCities.isEmpty()) {
            return Collections.emptyList();
        }

        return observedCities.stream()
                .map(observedCity -> ImmutablePair.of(observedCity, getCurrentTemperatureFor(observedCity)))
                .filter(pair -> pair.right != null)
                .map(pair -> new CityTemperatureInfo(pair.left, pair.right))
                .collect(Collectors.toList());
    }

    private ProviderCurrentTemperature getCurrentTemperatureFor(UserObservedCity userObservedCity) {
        List<ProviderCurrentTemperature> currentConditions = cityService.getCurrentConditions(userObservedCity.getExternalId());

        return ofNullable(currentConditions).orElseGet(Collections::emptyList).isEmpty() ?
                null : currentConditions.get(0);
    }
}
