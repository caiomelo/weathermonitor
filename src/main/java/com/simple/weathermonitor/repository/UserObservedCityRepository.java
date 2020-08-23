package com.simple.weathermonitor.repository;

import com.simple.weathermonitor.model.UserObservedCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserObservedCityRepository extends JpaRepository<UserObservedCity, Integer> {
}
