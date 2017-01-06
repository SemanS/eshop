package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Slavo on 10/17/2016.
 */
@Repository
public interface ItemDao extends JpaRepository<Item, Long>, QueryDslPredicateExecutor<Item> {

    Item findByUrl(String url);

    Item findById(Long id);
}
