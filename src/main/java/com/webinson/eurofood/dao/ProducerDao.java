package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.Producer;
import com.webinson.eurofood.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Slavo on 3/22/2017.
 */
public interface ProducerDao extends JpaRepository<Producer, Long>, QueryDslPredicateExecutor<Producer>, PagingAndSortingRepository<Producer, Long>, JpaSpecificationExecutor<Producer> {

    Producer findByName(String name);

    Producer findById(Long id);

}
