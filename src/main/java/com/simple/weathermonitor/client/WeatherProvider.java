package com.simple.weathermonitor.client;

import com.simple.weathermonitor.model.accuweather.ProviderCity;
import com.simple.weathermonitor.model.accuweather.ProviderCurrentTemperature;

import java.util.List;

public interface WeatherProvider {

    ProviderCity getCityInfo(String apiKey, String cityKey);

    List<ProviderCity> search(String apiKey, String searchText);

    List<ProviderCurrentTemperature> getCurrentConditions(String apiKey, String externalId);
}
