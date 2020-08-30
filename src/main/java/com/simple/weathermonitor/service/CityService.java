package com.simple.weathermonitor.service;

import com.simple.weathermonitor.client.WeatherProvider;
import com.simple.weathermonitor.configuration.ConfigurationService;
import com.simple.weathermonitor.exception.CitySearchException;
import com.simple.weathermonitor.model.accuweather.location.City;
import com.simple.weathermonitor.model.accuweather.temperature.ProviderCurrentTemperature;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@AllArgsConstructor
@Service
public class CityService {

    private final WeatherProvider provider;

    private final ConfigurationService configuration;

    public List<ProviderCurrentTemperature> getCurrentConditions(String externalId) {
        try {
            return provider.getCurrentConditions(configuration.getApiKey(), externalId);
        } catch (Exception e) {
            log.error("Failed retrieving current conditions for city key {}", externalId, e);
        }
        return emptyList();
    }

    public City getCityInfo(String cityKey) throws CitySearchException {
        try {
            return provider.getCityInfo(configuration.getApiKey(), cityKey);
        } catch (Exception e) {
            log.error("Failed retrieving info for city key {}", cityKey);
            throw new CitySearchException(e);
        }
    }

    public List<City> search(String searchText) throws CitySearchException {
        try {
            return provider.search(configuration.getApiKey(), searchText);
        } catch (Exception e) {
            log.error("Failed searching for {}", searchText, e);
            throw new CitySearchException(e);
        }
    }
}
