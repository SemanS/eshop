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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
    public Page<User> findByFilter(Map<String, String> filters, Pageable pageable) {
        return userDao.findAll(getFilterSpecification(filters), pageable);
    }

    private Specification<User> getFilterSpecification(Map<String, String> filterValues) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Optional<Predicate> predicate = filterValues.entrySet().stream()
                    .filter(v -> v.getValue() != null && v.getValue().length() > 0)
                    .map(entry -> {
                        Path<?> path = root;
                        String key = entry.getKey();
                        if (entry.getKey().contains(".")) {
                            String[] splitKey = entry.getKey().split("\\.");
                            path = root.join(splitKey[0]);
                            key = splitKey[1];
                        }
                        return builder.like(path.get(key).as(String.class), "%" + entry.getValue() + "%");
                    })
                    .collect(Collectors.reducing((a, b) -> builder.and(a, b)));
            return predicate.orElseGet(() -> alwaysTrue(builder));
        };
    }

    private Predicate alwaysTrue(CriteriaBuilder builder) {
        return builder.isTrue(builder.literal(true));
    }


    @Override
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<String>();
        for (User user : userDao.findAll()) {
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

        address.setCity(userDto.getAddressDtos().iterator().next().getCity());
        address.setPostalCode(userDto.getAddressDtos().iterator().next().getPostalCode());
        address.setStreet(userDto.getAddressDtos().iterator().next().getStreet());
        address.setFirstName(userDto.getAddressDtos().iterator().next().getFirstName());
        address.setLastName(userDto.getAddressDtos().iterator().next().getLastName());
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
