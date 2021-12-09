package com.softron.donation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softron.donation.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByStateIdOrderByName(Long stateId);

}
