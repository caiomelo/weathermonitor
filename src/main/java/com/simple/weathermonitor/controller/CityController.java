package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.model.accuweather.City;
import com.simple.weathermonitor.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/city")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/search")
    public List<City> search(@RequestParam String searchText) {
        return weatherService.search(searchText);
    }

    @GetMapping("/{cityKey}")
    public City getCityInfo(@PathVariable String cityKey) {
        return weatherService.getCityInfo(cityKey);
    }

}
