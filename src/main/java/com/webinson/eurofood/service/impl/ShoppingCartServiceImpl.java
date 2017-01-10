package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.assembler.CartItemAssembler;
import com.webinson.eurofood.assembler.ShoppingCartAssembler;
import com.webinson.eurofood.dao.ShoppingCartDao;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by Slavo on 1/7/2017.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Autowired
    ShoppingCartAssembler shoppingCartAssembler;

    @Autowired
    CartItemAssembler cartItemAssembler;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void saveShoppingCart(ShoppingCartDto shoppingCartDto, Set<CartItemDto> cartItemDtos) {

        ShoppingCart shoppingCart = shoppingCartAssembler.toModel(shoppingCartDto);

        for (CartItemDto cartItemDto : cartItemDtos) {
            CartItem cartItem = new CartItem();
            cartItem = cartItemAssembler.toModel(cartItemDto);
            cartItem.setShoppingCart(shoppingCart);
            shoppingCart.addCartItem(cartItem);
        }

        /*entityManager.persist(shoppingCart);*/
        shoppingCartDao.save(shoppingCart);
    }
}