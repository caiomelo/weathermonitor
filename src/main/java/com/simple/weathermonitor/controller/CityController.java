package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.model.accuweather.ProviderCity;
import com.simple.weathermonitor.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "City information", description = "Operations on cities and related information")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Operation(summary = "Search for a city",
            description = "Searches for a city using the given text")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProviderCity> search(@Parameter(description = "Text to search for") @RequestParam String searchText) {
        return cityService.search(searchText);
    }

    @Operation(summary = "Retrieve city information",
            description = "Retrieves information from the city identified by the given key")
    @GetMapping(value = "/{cityKey}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProviderCity getCityInfo(@Parameter(description = "City identifier key") @PathVariable String cityKey) {
        return cityService.getCityInfo(cityKey);
    }

}
