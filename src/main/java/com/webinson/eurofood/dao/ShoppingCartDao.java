package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Long>, QueryDslPredicateExecutor<ShoppingCart>, PagingAndSortingRepository<ShoppingCart, Long>, JpaSpecificationExecutor<ShoppingCart> {

    ShoppingCart findById(Long id);

}
