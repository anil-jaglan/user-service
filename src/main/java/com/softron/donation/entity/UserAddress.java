package com.softron.donation.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_ADDRESS")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 34489010671893993L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "HOUSE_NO", nullable = false)
    private String houseNo;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "STATE", nullable = false)
    private Integer state;

    @Column(name = "CITY", nullable = false)
    private Integer city;

    @Column(name = "LOCALITY", nullable = false)
    private Integer locality;

    @Column(name = "PINCODE", nullable = false)
    private Integer pincode;

    @Column(name = "IS_PRIMARY", nullable = false)
    private boolean primary;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;

    @Version
    private int version;

}
