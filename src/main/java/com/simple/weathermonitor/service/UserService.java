package com.simple.weathermonitor.service;

import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createOrUpdate(User user) {
        return repository.save(user);
    }

    public User getUserObservationFor(String email) {
        return repository.getUserObservation(email);
    }


}
