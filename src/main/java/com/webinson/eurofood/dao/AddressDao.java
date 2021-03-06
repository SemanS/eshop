package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Address;
import com.webinson.eurofood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Slavo on 12/6/2016.
 */
@Repository
public interface AddressDao extends JpaRepository<Address, Long>, QueryDslPredicateExecutor<Address> {
}
