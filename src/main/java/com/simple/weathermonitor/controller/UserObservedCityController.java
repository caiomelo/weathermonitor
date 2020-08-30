package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.model.CityTemperatureInfo;
import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.service.UserObservedCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Temperature observation", description = "Operations on users' temperature observations")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/observation")
public class UserObservedCityController {

    private final UserObservedCityService service;

    @Operation(summary = "Retrieve all observation info",
            description = "Retrieves all existing city temperature observation periods for all users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserObservedCity> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Add or update observation period",
            description = "Adds a city temperature observation period for a user or updates the existing one if a valid id is given")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserObservedCity createOrUpdate(@RequestBody @Valid UserObservedCity userObservedCity) {
        return service.createOrUpdate(userObservedCity);
    }

    @Operation(summary = "Retrieve active observation info for a user",
            description = "Retrieves the current temperature for all cities which are in an active observation period " +
                    "for the user identified by the given email")
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityTemperatureInfo> getObservedTemperaturesFor(
            @Parameter(description = "User email", required = true) @PathVariable String email) {
        return service.getCityObservationsFor(email);
    }
}
