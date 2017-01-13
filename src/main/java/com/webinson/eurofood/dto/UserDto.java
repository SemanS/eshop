package com.webinson.eurofood.dto;

import lombok.Data;

/**
 * Created by Slavo on 12/6/2016.
 */
@Data
public class UserDto {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String street;
    private String postalCode;
    private String city;
    private String company;
    private String ico;
    private String dic;

}
