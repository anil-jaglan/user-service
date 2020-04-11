package com.softron.donation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softron.donation.dto.UserAddressDTO;
import com.softron.donation.service.UserAddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "User Address Controller")
@Slf4j
public class UserAddessController {

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping(path = "/address/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Address")
    public UserAddressDTO getAddress(@PathVariable("userId") Long userId) {
        return userAddressService.getByUserId(userId).orElse(null);
    }

    @PostMapping(path = "/address", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Address")
    public ResponseEntity<Void> saveAddress(
            @ApiParam(name = "User Address", value = "User address information.", required = true) @Valid @RequestBody UserAddressDTO userAddress) {
        log.info("Adding new address: {}", userAddress);
        userAddressService.save(userAddress);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
