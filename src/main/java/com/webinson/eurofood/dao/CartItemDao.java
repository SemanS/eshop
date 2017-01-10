package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Slavo on 12/2/2016.
 */
@Repository
public interface CartItemDao extends JpaRepository<CartItem, Long>, QueryDslPredicateExecutor<CartItem> {

}
