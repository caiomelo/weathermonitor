package com.simple.weathermonitor.service;

import com.simple.weathermonitor.client.WeatherProvider;
import com.simple.weathermonitor.configuration.ConfigurationService;
import com.simple.weathermonitor.model.accuweather.ProviderCity;
import com.simple.weathermonitor.model.accuweather.ProviderCurrentTemperature;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CityService {

    private final WeatherProvider provider;

    private final ConfigurationService configuration;

    public List<ProviderCurrentTemperature> getCurrentConditions(String externalId) {
        return provider.getCurrentConditions(configuration.getApiKey(), externalId);
    }

    public ProviderCity getCityInfo(String cityKey) {
        return provider.getCityInfo(configuration.getApiKey(), cityKey);
    }

    public List<ProviderCity> search(String searchText) {
        return provider.search(configuration.getApiKey(), searchText);
    }


}
