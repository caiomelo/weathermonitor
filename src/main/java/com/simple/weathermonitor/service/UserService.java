package com.simple.weathermonitor.service;

import com.simple.weathermonitor.model.CityTemperatureInfo;
import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.model.accuweather.CurrentTemperature;
import com.simple.weathermonitor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private final UserObservedCityService observedCityService;

    private final WeatherService weatherService;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createOrUpdate(User user) {
        return repository.save(user);
    }

    public List<CityTemperatureInfo> getCityObservationsFor(String email) {
        List<UserObservedCity> observedCities = observedCityService.getActiveFor(email);

        if (observedCities == null || observedCities.isEmpty()) {
            return Collections.emptyList();
        }

        return observedCities.stream()
                .map(observedCity -> ImmutablePair.of(observedCity, getCurrentTemperatureFor(observedCity)))
                .filter(pair -> pair.right != null)
                .map(pair -> new CityTemperatureInfo(pair.left, pair.right))
                .collect(Collectors.toList());
    }

    private CurrentTemperature getCurrentTemperatureFor(UserObservedCity userObservedCity) {
        List<CurrentTemperature> currentConditions = weatherService.getCurrentConditions(userObservedCity.getExternalId());

        return ofNullable(currentConditions).orElseGet(Collections::emptyList).isEmpty() ?
                null : currentConditions.get(0);
    }
}
