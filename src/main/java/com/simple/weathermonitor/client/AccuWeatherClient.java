package com.simple.weathermonitor.client;

import com.simple.weathermonitor.model.accuweather.location.City;
import com.simple.weathermonitor.model.accuweather.temperature.TemperatureObservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "accuWeatherClient", url = "http://dataservice.accuweather.com")
public interface AccuWeatherClient extends WeatherProvider {

    @GetMapping("/locations/v1/{locationKey}")
    City getCityInfo(@RequestParam("apikey") String apiKey, @PathVariable("locationKey") String cityKey);

    @GetMapping("/locations/v1/cities/search")
    List<City> search(@RequestParam("apikey") String apiKey, @RequestParam("q") String searchText);

    @GetMapping("/currentconditions/v1/{locationKey}")
    List<TemperatureObservation> getCurrentConditions(@RequestParam("apikey") String apiKey, @PathVariable("locationKey") String cityKey);
}
