package com.simple.weathermonitor.service;

import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.repository.UserObservedCityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserObservedCityService {

    private final UserObservedCityRepository repository;

    public List<UserObservedCity> findAll() {
        return repository.findAll();
    }

    public UserObservedCity createOrUpdate(UserObservedCity userObservedCity) {
        return repository.save(userObservedCity);
    }

    public List<UserObservedCity> getActiveFor(String email) {
        return repository.getActiveFor(email);
    }
}
