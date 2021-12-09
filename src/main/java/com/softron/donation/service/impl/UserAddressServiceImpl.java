package com.softron.donation.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softron.donation.dto.UserAddressDTO;
import com.softron.donation.entity.UserAddress;
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
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Optional<UserAddressDTO> getByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId).map(userAddressmapper::toUserAddressDTO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserAddressDTO save(UserAddressDTO userAddress) {
        Optional<UserAddress> optUserAddress = userAddressRepository.findByUserId(userAddress.getUserId());
        UserAddress addressEntity = null;
        if (optUserAddress.isPresent()) {
            addressEntity = optUserAddress.get();
            BeanUtils.copyProperties(userAddress, addressEntity);
        } else {
            addressEntity = userAddressRepository.save(userAddressmapper.toUserAddress(userAddress));
        }
        log.info("User address saved.{}", addressEntity);
        return userAddressmapper.toUserAddressDTO(addressEntity);
    }

    @Override
    public void delete(Long id) {
        userAddressRepository.deleteById(id);
    }

}
