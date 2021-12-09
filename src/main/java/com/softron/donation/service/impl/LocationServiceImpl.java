package com.softron.donation.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softron.donation.dto.KeyValueDTO;
import com.softron.donation.entity.City;
import com.softron.donation.entity.Locality;
import com.softron.donation.entity.State;
import com.softron.donation.repository.StateRepository;
import com.softron.donation.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<KeyValueDTO> getAllLocations() {
        return stateRepository.findAll().stream().map(stateMapper()).collect(toList());
    }

    private Function<State, KeyValueDTO> stateMapper() {
        return state -> KeyValueDTO.builder().id(state.getId()).name(state.getName())
                .children(state.getCities().stream().map(cityMapper()).collect(toList())).build();
    }

    private Function<City, KeyValueDTO> cityMapper() {
        return city -> KeyValueDTO.builder().id(city.getId()).name(city.getName())
                .children(city.getLocalities().stream().map(localityMapper()).collect(toList())).build();
    }

    private Function<Locality, KeyValueDTO> localityMapper() {
        return loc -> KeyValueDTO.builder().id(loc.getId()).name(loc.getName()).build();
    }

}
