package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.Address;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Slavo on 12/6/2016.
 */
public interface UserService {

    List<String> getAllUsers();

    void registerNewUserAccount(UserDto userDto);

    void addressCompanySave();

    AddressDto getAddressByUsername(String username);

    Page<User> findByFilter(Map<String, String> filters, Pageable pageable);

}

