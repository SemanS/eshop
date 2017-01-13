package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.dao.AddressDao;
import com.webinson.eurofood.dao.AuthorityDao;
import com.webinson.eurofood.dao.CompanyDao;
import com.webinson.eurofood.dao.UserDao;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.*;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private AddressDao addressDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<String>();
        for(User user : userDao.findAll()) {
            users.add(user.getUsername());
        }
        return users;
    }

    @Override
    public void registerNewUserAccount(UserDto userDto) {

        /*if (emailExist(userDto.getEmail())) {

        }*/
        User user = new User();
        Authority authority = new Authority();
        Company company = new Company();
        Address address = new Address();

        user.setUsername(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());

        authority.setUsername(userDto.getEmail());
        authority.setAuthority("ROLE_USER");

        address.setCity(userDto.getCity());
        address.setPostalCode(userDto.getPostalCode());
        address.setStreet(userDto.getStreet());
        address.setUser(user);
        address.setFirstName(userDto.getFirstName());
        address.setLastName(userDto.getLastName());

        company.setDic(userDto.getDic());
        company.setIco(userDto.getIco());
        company.setName(userDto.getCompany());
        company.setUser(user);

        userDao.save(user);
        authorityDao.save(authority);
        companyDao.save(company);
        addressDao.save(address);

    }

    @Override
    public void addressCompanySave() {

    }

}
