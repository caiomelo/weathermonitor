package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.client.WeatherServiceClient;
import com.simple.weathermonitor.model.accuweather.City;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {

    private static final String API_KEY = "svG4gWkiFwXjFGKllIefcDEvfL2JVsKT";

    private final WeatherServiceClient weatherServiceClient;

    @GetMapping("/search")
    public List<City> search(@RequestParam String searchText) {
        return weatherServiceClient.search(API_KEY, searchText);
    }

    @GetMapping("/{cityKey}")
    public City getCityInfo(@PathVariable String cityKey) {
        return weatherServiceClient.getCityInfo(API_KEY, cityKey);
    }

}
