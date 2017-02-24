package com.webinson.eurofood.dto;

import lombok.Data;

/**
 * Created by Slavo on 1/13/2017.
 */
@Data
public class AddressDto {

    private String street;
    private String postalCode;
    private String city;
    private String firstName;
    private String lastName;
    private Long userDtoId;

    public String toString() {
        return firstName + " " + lastName + " " + street + " " + postalCode + " " + city;
    }

}
