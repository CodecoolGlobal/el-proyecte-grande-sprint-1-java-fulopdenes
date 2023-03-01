package com.codecool.TaskTiger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence",
            sequenceName = "address_sequence")
    @GeneratedValue(strategy = SEQUENCE,
            generator = "address_sequence"
    )
    private Long id;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "zipcode", nullable = false)
    private String zipcode;
    @Column(name = "county", nullable = false)
    private String county;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "street_nr", nullable = false)
    private int street_nr;
    @Column(name = "building")
    private String building;
}
