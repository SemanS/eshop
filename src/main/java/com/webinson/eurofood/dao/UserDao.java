package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.*;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Slavo on 12/6/2016.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
    User findByUsername(String name);
    List<User> findAll();
}
