package com.simple.weathermonitor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity(name = "USERS")
public class User {

    @Id
    @SequenceGenerator(name = "USERS_GENERATOR", sequenceName = "USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USERS_GENERATOR")
    private Integer id;

    @Size(max = 30, message = "Max 30 characters")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @JsonManagedReference("user-observedCity")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserObservedCity> observedCities;

}
