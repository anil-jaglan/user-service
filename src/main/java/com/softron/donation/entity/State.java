package com.softron.donation.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STATE_INFO")
public class State implements Serializable {

    private static final long serialVersionUID = 6304565586027697118L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATE_ID")
    private Long id;

    @Column(name = "STATE_NAME", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<City> cities;

}
