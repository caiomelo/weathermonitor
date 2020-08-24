package com.simple.weathermonitor.repository;

import com.simple.weathermonitor.model.UserObservedCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserObservedCityRepository extends JpaRepository<UserObservedCity, Integer> {

    @Query("SELECT oc FROM Users u left join u.observedCities oc WHERE u.email = :email AND " +
            "CURRENT_TIMESTAMP BETWEEN oc.observationPeriodStart and oc.observationPeriodEnd")
    List<UserObservedCity> getActiveFor(@Param("email") String email);

}
