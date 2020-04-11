package com.softron.donation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.softron.donation.dto.UserAddressDTO;
import com.softron.donation.entity.UserAddress;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {

    UserAddressMapper INSTANCE = Mappers.getMapper(UserAddressMapper.class);

    UserAddressDTO toUserAddressDTO(UserAddress userAddress);

    UserAddress toUserAddress(UserAddressDTO userAddress);

}
