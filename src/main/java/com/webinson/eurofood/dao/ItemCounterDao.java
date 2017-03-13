package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Company;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.ItemCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Slavo on 3/9/2017.
 */
@Repository
public interface ItemCounterDao extends JpaRepository<ItemCounter, Long>, QueryDslPredicateExecutor<ItemCounter> {
    ItemCounter findByItemId(Long id);
}
