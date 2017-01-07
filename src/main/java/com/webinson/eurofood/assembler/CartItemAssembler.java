package com.webinson.eurofood.assembler;

import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Slavo on 1/7/2017.
 */
@Component
public class CartItemAssembler {

    @Autowired
    ItemAssembler itemAssembler;

    public CartItemDto convertToDto(CartItem model, CartItemDto dto) {
        dto.setId(model.getId());
        dto.setItemId(model.getItemId());
        dto.setQuantity(model.getQuantity());
        return dto;
    }

    public CartItem convertToModel(CartItemDto dto, CartItem model) {
        model.setItemId(dto.getItemId());
        model.setQuantity(dto.getQuantity());
        return model;
    }

    public CartItemDto toDto(CartItem model) {
        CartItemDto dto = new CartItemDto();
        dto.setId(model.getId());
        dto.setItemId(model.getItemId());
        dto.setQuantity(model.getQuantity());
        return dto;
    }

    public Set<CartItemDto> toDtos(final Collection<CartItem> models) {
        final Set<CartItemDto> dtos = new HashSet<>();
        if (isNotEmpty(models)) {
            for (final CartItem cartItem : models) {
                dtos.add(convertToDto(cartItem, new CartItemDto()));
            }
        }
        return dtos;
    }

    public Set<CartItem> toModels(final Collection<CartItemDto> dtos) {
        final Set<CartItem> models = new HashSet<>();
        if (isNotEmpty(dtos)) {
            for (final CartItemDto cartItemDto : dtos) {
                models.add(convertToModel(cartItemDto, new CartItem()));
            }
        }
        return models;
    }

    public boolean isNotEmpty(final Collection<?> col) {
        return !isEmpty(col);
    }

    public boolean isEmpty(final Collection<?> col) {
        return col == null || col.isEmpty();
    }
}
