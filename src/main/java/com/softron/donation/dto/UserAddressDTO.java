package com.softron.donation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserAddressDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "UserId can't be null")
    @ApiModelProperty(name = "User Id", allowEmptyValue = false, required = true)
    private Long userId;

    @NotBlank(message = "House number can't be blank")
    @Size(min = 1, max = 200, message = "House number length should be between 1 and 200")
    @ApiModelProperty(name = "House number", allowEmptyValue = false, required = true)
    private String houseNo;

    @NotBlank(message = "Street address can't be blank")
    @Size(min = 1, max = 200, message = "Street length should be between 1 and 200")
    @ApiModelProperty(name = "Street address.", allowEmptyValue = false, required = true)
    private String street;

    @NotNull(message = "State can't be empty")
    @ApiModelProperty(name = "State code", allowEmptyValue = false, required = true)
    private Integer state;

    @NotNull(message = "City can't be empty")
    @ApiModelProperty(name = "City code", allowEmptyValue = false, required = true)
    private Integer city;

    @NotNull(message = "Locality can't be empty")
    @ApiModelProperty(name = "Locality code", allowEmptyValue = false, required = true)
    private Integer locality;

    @NotNull(message = "Pincode can't be empty")
    @ApiModelProperty(name = "Pincode", allowEmptyValue = false, required = true)
    private Integer pincode;

}
