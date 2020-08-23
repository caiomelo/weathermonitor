package com.simple.weathermonitor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class UserObservedCity {

    @Id
    @SequenceGenerator(name = "USEROBSERVEDCIRY_GENERATOR", sequenceName = "USEROBSERVEDCIRY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USEROBSERVEDCIRY_GENERATOR")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USERID")
    private User user;

    private String externalId;

    private Date observationPeriodStart;

    private Date observationPeriodEnd;
}
