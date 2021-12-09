package com.softron.donation.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITY_INFO")
public class City implements Serializable {

    private static final long serialVersionUID = -6657763167385168028L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_ID")
    private Long id;

    @Column(name = "CITY_NAME", length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "STATE_ID", nullable = false)
    private State state;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Locality> localities;

}
