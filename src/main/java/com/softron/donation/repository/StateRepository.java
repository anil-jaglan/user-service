package com.softron.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softron.donation.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
