package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.dao.ShoppingCartDao;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Slavo on 1/7/2017.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Override
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartDao.save(shoppingCart);
    }
}