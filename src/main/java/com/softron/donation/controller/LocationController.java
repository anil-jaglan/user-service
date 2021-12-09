package com.softron.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softron.donation.dto.LocationDTO;
import com.softron.donation.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "Location Controller")
@Slf4j
@CrossOrigin("http://localhost:3000")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(path = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all location data")
    public LocationDTO getLocations() {
        log.debug("Getting all locations");
        return LocationDTO.builder().states(locationService.getAllLocations()).build();
    }

}
