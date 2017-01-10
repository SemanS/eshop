package com.webinson.eurofood.bean;

import com.webinson.eurofood.assembler.CartItemAssembler;
import com.webinson.eurofood.assembler.ShoppingCartAssembler;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.service.CartItemService;
import com.webinson.eurofood.service.ShoppingCartService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Slavo on 1/6/2017.
 */
@Scope("session")
@Component
public class ShoppingCartView {

    @Getter
    @Setter
    private int counter;

    /*@Getter
    @Setter
    private List<ItemDto> items = new ArrayList<ItemDto>();*/

    /*@Getter
    @Setter
    private CartItemDto cartItemDto = new CartItemDto();*/

    @Getter
    @Setter
    private Set<CartItemDto> cartItemDtos;

    @Getter
    @Setter
    private ShoppingCartDto shoppingCartDto;

    @Getter
    @Setter
    private float total;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartAssembler shoppingCartAssembler;

    @Autowired
    CartItemAssembler cartItemAssembler;

    @Autowired
    CartItemService cartItemService;

    @PostConstruct
    public void init() {
        /*cartItemDto = new CartItemDto();*/
        shoppingCartDto = new ShoppingCartDto();
        cartItemDtos = new HashSet<>();
    }

    public void addItemToCart(ItemDto itemDto) {

        /*this.items.add(itemDto);
        System.out.println(items.size());*/

        CartItemDto cartItemDto = new CartItemDto();

        if (cartItemDtos.size() == 0) {
            cartItemDto.setItemId(itemDto.getId());
            this.cartItemDtos.add(cartItemDto);
            //this.shoppingCartDto.setCartItemDtos(cartItemDtos);
            /*this.shoppingCartDto.addCartItem(cartItemDto);*/
        } else {
            for (CartItemDto cartI : cartItemDtos) {
                if (cartItemDto.getItemId() == itemDto.getId()) {
                    cartItemDto.setQuantity(cartItemDto.getQuantity() + 0);
                } else {
                    cartItemDto.setItemId(itemDto.getId());
                    cartItemDto.setQuantity(0);
                    cartItemDtos.add(cartItemDto);
                    System.out.println(cartItemDtos.size());
                }
            }
        }
    }

    public void checkout() {
        /*this.shoppingCartDto.setCartItemDtos(this.cartItemDtos);*/

        /*for (CartItemDto cartItemDto : this.cartItemDtos) {
            cartItemService.saveCartItem(cartItemAssembler.toModel(cartItemDto));
        }*/

        shoppingCartService.saveShoppingCart(this.shoppingCartDto, this.cartItemDtos);
    }

    public void testovaciaMetoda(ItemDto itemDto) {
        counter++;
    }

}
