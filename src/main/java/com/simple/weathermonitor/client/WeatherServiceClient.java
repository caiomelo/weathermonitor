package com.simple.weathermonitor.client;

import com.simple.weathermonitor.model.accuweather.City;
import com.simple.weathermonitor.model.accuweather.CurrentTemperature;

import java.util.List;

public interface WeatherServiceClient {

    City getCityInfo(String apiKey, String locationKey);

    List<City> search(String apiKey, String searchText);

    List<CurrentTemperature> getCurrentConditions(String apiKey, String externalId);
}
