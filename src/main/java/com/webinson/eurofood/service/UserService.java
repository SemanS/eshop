package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.User;

import java.util.List;

/**
 * Created by Slavo on 12/6/2016.
 */
public interface UserService {

    List<UserDto> getAllUsers();

    User registerNewUserAccount(UserDto userDto);
}

