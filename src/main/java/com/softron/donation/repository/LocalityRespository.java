package com.softron.donation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softron.donation.entity.Locality;

@Repository
public interface LocalityRespository extends JpaRepository<Locality, Long> {

    List<Locality> findByCityIdOrderByName(Long cityId);

}
