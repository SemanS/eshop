package com.webinson.eurofood.dto;

import lombok.Data;

/**
 * Created by Slavo on 1/7/2017.
 */
@Data
public class CartItemDto {

    private Long id;
    private ItemDto itemDto;
    private int quantity;

}
