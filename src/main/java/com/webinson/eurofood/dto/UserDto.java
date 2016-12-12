package com.webinson.eurofood.dto;

import lombok.Data;

/**
 * Created by Slavo on 12/6/2016.
 */
@Data
public class UserDto {
    private String name;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String postalCode;
    private String city;

}
