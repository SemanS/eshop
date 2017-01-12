package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.dao.AuthorityDao;
import com.webinson.eurofood.dao.UserDao;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.Authority;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Slavo on 12/7/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {

        /*if (emailExist(userDto.getEmail())) {

        }*/
        User user = new User();
        Authority authority = new Authority();
        user.setUsername(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        authority.setUsername(userDto.getEmail());
        authority.setAuthority("ROLE_USER");
        authorityDao.save(authority);
        /*user.setRole(new Role(Integer.valueOf(1), user));*/

        return userDao.save(user);

    }
}
