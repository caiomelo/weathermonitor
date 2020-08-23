package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.service.UserObservedCityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/userobservedcity")
public class UserObservedCityController {

    private final UserObservedCityService service;

    @GetMapping
    public List<UserObservedCity> findAll() {
        return service.findAll();
    }

    @PostMapping
    public UserObservedCity createOrUpdate(@RequestBody @Valid UserObservedCity userObservedCity) {
        return service.createOrUpdate(userObservedCity);
    }
}
