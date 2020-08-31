package com.simple.weathermonitor.service;

import com.simple.weathermonitor.exception.SaveObservationException;
import com.simple.weathermonitor.model.CityTemperatureInfo;
import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.model.accuweather.temperature.TemperatureObservation;
import com.simple.weathermonitor.repository.UserObservedCityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@AllArgsConstructor
@Service
public class UserObservedCityService {

    private final UserObservedCityRepository repository;

    private final CityService cityService;

    public List<UserObservedCity> findAll() {
        return repository.findAll();
    }

    public UserObservedCity createOrUpdate(UserObservedCity userObservedCity) throws SaveObservationException {
        try {
            return repository.save(userObservedCity);
        } catch (Exception e) {
            log.error("Failed saving observation period {}", userObservedCity, e);
            throw new SaveObservationException(e);
        }
    }

    public List<CityTemperatureInfo> getCityObservationsFor(String email) {
        List<UserObservedCity> observedCities = repository.getActiveFor(email);

        if (isEmpty(observedCities)) {
            return Collections.emptyList();
        }

        return observedCities.stream()
                .map(observedCity -> ImmutablePair.of(observedCity, getCurrentTemperatureFor(observedCity)))
                .filter(pair -> pair.right != null)
                .map(pair -> new CityTemperatureInfo(pair.left, pair.right))
                .collect(Collectors.toList());
    }

    private TemperatureObservation getCurrentTemperatureFor(UserObservedCity userObservedCity) {
        List<TemperatureObservation> currentConditions = cityService.getCurrentConditions(userObservedCity.getExternalId());
        return ofNullable(currentConditions).orElseGet(Collections::emptyList).isEmpty() ?
                null : currentConditions.get(0);
    }
}
