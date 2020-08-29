package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.service.UserObservedCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User observed city", description = "Operations on user's observed city")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/userobservedcity")
public class UserObservedCityController {

    private final UserObservedCityService service;

    @Operation(summary = "Retrieve cities observation periods",
            description = "Retrieves all existing city observation periods for all users")
    @GetMapping
    public List<UserObservedCity> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Add or update city observation period",
            description = "Adds a city observation period for a user or updates the existing one if a valid id is given")
    @PostMapping
    public UserObservedCity createOrUpdate(@RequestBody @Valid UserObservedCity userObservedCity) {
        return service.createOrUpdate(userObservedCity);
    }
}
