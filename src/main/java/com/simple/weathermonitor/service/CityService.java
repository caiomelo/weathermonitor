package com.simple.weathermonitor.service;

import com.simple.weathermonitor.client.WeatherProvider;
import com.simple.weathermonitor.configuration.ConfigurationService;
import com.simple.weathermonitor.model.accuweather.location.City;
import com.simple.weathermonitor.model.accuweather.temperature.ProviderCurrentTemperature;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Service
public class CityService {

    private final WeatherProvider provider;

    private final ConfigurationService configuration;

    public List<ProviderCurrentTemperature> getCurrentConditions(String externalId) {
        return provider.getCurrentConditions(configuration.getApiKey(), externalId);
    }

    public City getCityInfo(String cityKey) {
        return ofNullable(provider.getCityInfo(configuration.getApiKey(), cityKey))
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<City> search(String searchText) {
        return provider.search(configuration.getApiKey(), searchText);
    }
}
