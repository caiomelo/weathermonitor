package com.simple.weathermonitor.client;

import com.simple.weathermonitor.model.accuweather.location.City;
import com.simple.weathermonitor.model.accuweather.temperature.TemperatureObservation;

import java.util.List;

public interface WeatherProvider {

    City getCityInfo(String apiKey, String cityKey);

    List<City> search(String apiKey, String searchText);

    List<TemperatureObservation> getCurrentConditions(String apiKey, String externalId);
}
