package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.ShoppingCart;

import java.util.Set;

/**
 * Created by Slavo on 1/7/2017.
 */
public interface ShoppingCartService {

    public void saveShoppingCart(ShoppingCartDto shoppingCartDto, Set<CartItemDto> cartItemDtos);

}
