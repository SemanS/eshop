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
    private int postalCode;
    private String city;
    private String company;
    private int ico;
    private String dic;

}
