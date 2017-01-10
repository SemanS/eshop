package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.dao.CartItemDao;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CartItemService;
import com.webinson.eurofood.service.CategoryService;
import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemDao cartItemDao;


    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItemDao.save(cartItem);
    }
}
