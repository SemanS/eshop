package com.webinson.eurofood.dto;

import com.webinson.eurofood.entity.Producer;
import lombok.Data;

import java.util.Set;

/**
 * Created by Slavo on 1/7/2017.
 */
@Data
public class ShoppingCartDto {

    private Long id;

    private Set<CartItemDto> cartItemDtos;

}
