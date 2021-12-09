package com.softron.donation.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LOCALITY_INFO")
public class Locality implements Serializable {

    private static final long serialVersionUID = 3297452248269692964L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOCALITY_NAME", length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;
}
