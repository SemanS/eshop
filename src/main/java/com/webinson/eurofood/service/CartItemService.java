package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.ShoppingCart;
import org.omnifaces.model.tree.TreeModel;

/**
 * Created by Slavo on 12/2/2016.
 */
public interface CartItemService {

    public void saveCartItem(CartItem cartItem);

}
