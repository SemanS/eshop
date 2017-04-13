package com.webinson.eurofood.dto;

import lombok.Data;

/**
 * Created by Slavo on 1/7/2017.
 */
@Data
public class CartItemDto {

    private Long id;
    private Long itemId;
    private ItemDto itemDto;
    private int quantity;
    private Long shoppingCartId;

    public boolean equals(Object o) {
        if (!(o instanceof CartItemDto)) {
            return false;
        }
        CartItemDto other = (CartItemDto) o;
        return itemDto.getHeader().equals(other.itemDto.getHeader()) && id.equals(other.id);
    }

    public int hashCode() {
        return itemDto.getHeader().hashCode();
    }

}
