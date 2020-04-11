package com.softron.donation.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softron.donation.dto.UserAddressDTO;
import com.softron.donation.mapper.UserAddressMapper;
import com.softron.donation.repository.UserAddressRepository;
import com.softron.donation.service.UserAddressService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserAddressMapper userAddressmapper;

    @Override
    public Optional<UserAddressDTO> getByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId).map(userAddressmapper::toUserAddressDTO);
    }

    @Override
    public void save(UserAddressDTO userAddress) {
        userAddressRepository.save(userAddressmapper.toUserAddress(userAddress));
        log.info("User address saved.");
    }

    @Override
    public void delete(Long id) {
        userAddressRepository.deleteById(id);
    }

}
