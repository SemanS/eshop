package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * Created by Slavo on 1/7/2017.
 */
public interface ShoppingCartService {

    public void saveShoppingCart(ShoppingCartDto shoppingCartDto, Set<CartItemDto> cartItemDtos);

    Page<ShoppingCart> findByFilter(Map<String, String> filters, Pageable pageable);

    public int getLastId();

}
