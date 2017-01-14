package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.Address;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.entity.User;

import java.util.List;

/**
 * Created by Slavo on 12/6/2016.
 */
public interface UserService {

    List<String> getAllUsers();

    void registerNewUserAccount(UserDto userDto);

    void addressCompanySave();

    AddressDto getAddressByUsername(String username);

}

