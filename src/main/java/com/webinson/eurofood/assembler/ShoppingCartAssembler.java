package com.webinson.eurofood.assembler;

import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Slavo on 1/7/2017.
 */
@Component
public class ShoppingCartAssembler {

    @Autowired
    CartItemAssembler cartItemAssembler;

    public ShoppingCartDto convertToDto(ShoppingCart model, ShoppingCartDto dto) {
        dto.setId(model.getId());
        dto.setFacturationAddress(model.getFacturationAddress());
        dto.setDeliveryAddress(model.getDeliveryAddress());
        return dto;
    }

    public ShoppingCartDto toDto(ShoppingCart model) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(model.getId());
        dto.setFacturationAddress(model.getFacturationAddress());
        dto.setDeliveryAddress(model.getDeliveryAddress());
        return dto;
    }

    public ShoppingCart toModel(ShoppingCartDto shoppingCartDto) {
        ShoppingCart model = new ShoppingCart();
        model.setFacturationAddress(shoppingCartDto.getFacturationAddress());
        model.setDeliveryAddress(shoppingCartDto.getDeliveryAddress());
        return model;
    }

    public List<ShoppingCartDto> toDtos(final Collection<ShoppingCart> models) {
        final List<ShoppingCartDto> dtos = new ArrayList<>();
        if (isNotEmpty(models)) {
            for (final ShoppingCart shoppingCart : models) {
                dtos.add(convertToDto(shoppingCart, new ShoppingCartDto()));
            }
        }
        return dtos;
    }

    public boolean isNotEmpty(final Collection<?> col) {
        return !isEmpty(col);
    }

    public boolean isEmpty(final Collection<?> col) {
        return col == null || col.isEmpty();
    }
}