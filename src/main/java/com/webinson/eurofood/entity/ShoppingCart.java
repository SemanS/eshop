package com.webinson.eurofood.entity;

import com.webinson.eurofood.dto.ItemDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavo on 12/5/2016.
 */

@Scope("session")
@Component
public class ShoppingCart {

    @Getter
    @Setter
    private int counter;

    @Getter
    @Setter
    private List<ItemDto> items = new ArrayList<ItemDto>();

    @Getter
    @Setter
    private float total;

    public void testovaciaMetoda(ItemDto itemDto) {

        counter++;
    }

}
