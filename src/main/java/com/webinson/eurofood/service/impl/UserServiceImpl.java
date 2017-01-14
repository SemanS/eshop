package com.webinson.eurofood.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.webinson.eurofood.assembler.AddressAssembler;
import com.webinson.eurofood.dao.AddressDao;
import com.webinson.eurofood.dao.AuthorityDao;
import com.webinson.eurofood.dao.CompanyDao;
import com.webinson.eurofood.dao.UserDao;
import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.*;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private AddressAssembler addressAssembler;

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

        address.setCity(userDto.getAddressDtos().get(0).getCity());
        address.setPostalCode(userDto.getAddressDtos().get(0).getPostalCode());
        address.setStreet(userDto.getAddressDtos().get(0).getStreet());
        address.setFirstName(userDto.getAddressDtos().get(0).getFirstName());
        address.setLastName(userDto.getAddressDtos().get(0).getLastName());
        address.setUser(user);

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

    @Override
    public AddressDto getAddressByUsername(String username) {
        Category category = new Category();

        final JPAQuery<Address> query = new JPAQuery<>(entityManager);
        QAddress address = QAddress.address;
        QUser user = QUser.user;
        Address address1 = query.from(address).select(address).where(user.username.eq(username)).fetchFirst();
        /*List<Item> items = query.from(item).select(item).where(item.category.id.eq(categoryDto.getId())).fetch();*/
        return addressAssembler.toDto(address1);

    }

}
