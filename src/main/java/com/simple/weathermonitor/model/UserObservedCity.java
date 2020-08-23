package com.simple.weathermonitor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class UserObservedCity {

    @Id
    @SequenceGenerator(name = "USEROBSERVEDCITY_GENERATOR", sequenceName = "USEROBSERVEDCITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USEROBSERVEDCITY_GENERATOR")
    private Integer id;

    @JsonBackReference("user-observedCity")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    private String externalId;

    private Date observationPeriodStart;

    private Date observationPeriodEnd;
}
