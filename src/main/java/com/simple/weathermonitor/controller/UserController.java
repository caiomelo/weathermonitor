package com.simple.weathermonitor.controller;

import com.simple.weathermonitor.model.CityTemperatureInfo;
import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User", description = "Operations on users")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Operation(summary = "Retrieve all users", description = "Retrieves a list of all existing users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Create or update a user",
            description = "Creates a new user or updates an existing one if a valid id is given")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User createOrUpdate(@RequestBody @Valid User user) {
        return service.createOrUpdate(user);
    }

    @Operation(summary = "Retrieve temperature from active observed cities for a user",
            description = "Retrieves the current temperature for all cities which are in an active observation period " +
                    "for the user identified by the given email")
    @GetMapping(value = "{email}/observation", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityTemperatureInfo> getObservedTemperaturesFor(
            @Parameter(description = "User email", required = true) @PathVariable String email) {
        return service.getCityObservationsFor(email);
    }
}
