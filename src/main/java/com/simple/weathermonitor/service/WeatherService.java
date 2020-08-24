package com.simple.weathermonitor.service;

import com.simple.weathermonitor.client.WeatherServiceClient;
import com.simple.weathermonitor.configuration.ConfigurationService;
import com.simple.weathermonitor.model.accuweather.City;
import com.simple.weathermonitor.model.accuweather.CurrentTemperature;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WeatherService {

    private final WeatherServiceClient client;

    private final ConfigurationService configuration;

    public List<CurrentTemperature> getCurrentConditions(String externalId) {
        return client.getCurrentConditions(configuration.getApiKey(), externalId);
    }

    public City getCityInfo(String locationKey) {
        return client.getCityInfo(configuration.getApiKey(), locationKey);
    }

    public List<City> search(String searchText) {
        return client.search(configuration.getApiKey(), searchText);
    }


}
