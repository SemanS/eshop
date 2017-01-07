package com.webinson.eurofood.bean;

import com.webinson.eurofood.assembler.ShoppingCartAssembler;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
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
/*@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")*/
@Scope("session")
@Component
public class ShoppingCartView {

    @Getter
    @Setter
    private int counter;

    /*@Getter
    @Setter
    private List<ItemDto> items = new ArrayList<ItemDto>();*/

    @Getter
    @Setter
    private CartItemDto cartItemDto;

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

    @PostConstruct
    public void init() {
        cartItemDtos = new HashSet<>();
        shoppingCartDto = new ShoppingCartDto();
        cartItemDto = new CartItemDto();
    }

    public void addItemToCart(ItemDto itemDto) {

        /*this.items.add(itemDto);
        System.out.println(items.size());*/

        if (cartItemDtos.size() == 0) {
            this.cartItemDto.setItemDto(itemDto);
            this.cartItemDtos.add(cartItemDto);
            System.out.println(cartItemDtos.size());
        } else {
            for (CartItemDto cartItemDto : cartItemDtos) {
                if (cartItemDto.getItemDto().getHeader() == itemDto.getHeader()) {
                    cartItemDto.setQuantity(cartItemDto.getQuantity() + 0);
                } else {
                    this.cartItemDto.setItemDto(itemDto);
                    this.cartItemDto.setQuantity(0);
                    this.cartItemDtos.add(cartItemDto);
                    System.out.println(cartItemDtos.size());
                }
            }
        }
    }

    public void checkout() {
        this.shoppingCartDto.setCartItemDtos(cartItemDtos);
        shoppingCartService.saveShoppingCart(shoppingCartAssembler.toModel(this.shoppingCartDto));
    }

    public void testovaciaMetoda(ItemDto itemDto) {
        counter++;
    }

}
