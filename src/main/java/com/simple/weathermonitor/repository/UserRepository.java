package com.simple.weathermonitor.repository;

import com.simple.weathermonitor.model.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM Users u LEFT JOIN u.observedCities oc WHERE" +
            " u.email = :email" +
            " AND CURRENT_TIMESTAMP BETWEEN oc.observationPeriodStart AND oc.observationPeriodEnd")
    User getUserObservation(@Param("email") String email);

}
