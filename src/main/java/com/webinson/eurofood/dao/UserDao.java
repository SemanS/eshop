package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Slavo on 12/6/2016.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
}
