package com.simple.weathermonitor.service;

import com.simple.weathermonitor.exception.SaveUserException;
import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createOrUpdate(User user) throws SaveUserException {
        try {
            return repository.save(user);
        } catch (Exception e) {
            log.error("Failed saving user {}", user, e);
            throw new SaveUserException(e);
        }
    }
}
