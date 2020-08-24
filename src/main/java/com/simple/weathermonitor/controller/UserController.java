package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.model.CityTemperatureInfo;
import com.simple.weathermonitor.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @PostMapping
    public User createOrUpdate(@RequestBody @Valid User user) {
        return service.createOrUpdate(user);
    }

    @GetMapping("{email}/observation")
    public List<CityTemperatureInfo> getObservedTemperaturesFor(@PathVariable String email) {
        return service.getCityObservationsFor(email);
    }
}
