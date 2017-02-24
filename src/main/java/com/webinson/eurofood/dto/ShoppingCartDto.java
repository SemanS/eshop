package com.webinson.eurofood.dto;

import lombok.Data;
import java.util.List;

/**
 * Created by Slavo on 1/7/2017.
 */
@Data
public class ShoppingCartDto {

    private Long id;

    private List<CartItemDto> cartItemDtos;

    private String orderAddress;

}
