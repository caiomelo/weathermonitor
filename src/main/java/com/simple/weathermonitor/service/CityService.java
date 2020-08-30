package com.simple.weathermonitor.service;

import com.simple.weathermonitor.client.WeatherProvider;
import com.simple.weathermonitor.configuration.ConfigurationService;
import com.simple.weathermonitor.model.accuweather.City;
import com.simple.weathermonitor.model.accuweather.CurrentTemperature;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CityService {

    private final WeatherProvider provider;

    private final ConfigurationService configuration;

    public List<CurrentTemperature> getCurrentConditions(String externalId) {
        return provider.getCurrentConditions(configuration.getApiKey(), externalId);
    }

    public City getCityInfo(String cityKey) {
        return provider.getCityInfo(configuration.getApiKey(), cityKey);
    }

    public List<City> search(String searchText) {
        return provider.search(configuration.getApiKey(), searchText);
    }


}
