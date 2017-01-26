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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    private int counter = 0;
    @Getter
    @Setter
    private double counterNetto = 0.00;
    @Getter
    @Setter
    private double counterBrutto = 0.00;
    @Getter
    @Setter
    private int shoppingCartQuantity = 1;
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
        shoppingCartDto = new ShoppingCartDto();
        cartItemDtos = new HashSet<>();
    }

    public String onRestartCounter() {
        this.counter = 0;
        this.cartItemDtos = new HashSet<>();
        this.counterNetto = 0;
        this.counterBrutto = 0;
        return "pretty:";
    }

    public String addItemToCart(ItemDto itemDto) {

        if (shoppingCartQuantity >= 1) {

            CartItemDto cartItemDto = new CartItemDto();
            boolean isInCart = false;
            boolean isInSecondCart = false;
            int counterOfProduct = 0;

            if (cartItemDtos.size() == 0) {
                cartItemDto.setItemId(itemDto.getId());
                cartItemDto.setQuantity(shoppingCartQuantity);
                cartItemDto.setItemDto(itemDto);
                counter = counter + shoppingCartQuantity;
                counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                this.cartItemDtos.add(cartItemDto);
            } else {
                for (CartItemDto cartI : cartItemDtos) {
                    if (cartI.getItemId() == itemDto.getId()) {
                        isInCart = true;
                    }
                }
            }
            if (isInCart == true) {
                for (CartItemDto cartI : cartItemDtos) {
                    if (cartI.getItemId() == itemDto.getId()) {
                        counterOfProduct = cartI.getQuantity() + shoppingCartQuantity;
                        cartI.setQuantity(counterOfProduct);
                    }
                }
                counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                counter = counter + shoppingCartQuantity;


            } else if (cartItemDtos.size() != 1) {
                counter = counter + shoppingCartQuantity;
                counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                cartItemDto.setItemId(itemDto.getId());
                cartItemDto.setItemDto(itemDto);
                cartItemDto.setQuantity(0);
                this.cartItemDtos.add(cartItemDto);
            }

            if (isInCart == false && cartItemDtos.size() != 0) {
                for (CartItemDto cartI : cartItemDtos) {
                    if (cartI.getItemId() != itemDto.getId()) {
                        isInSecondCart = true;
                    }
                }
                if (isInSecondCart == true) {
                    cartItemDto.setItemId(itemDto.getId());
                    cartItemDto.setItemDto(itemDto);
                    cartItemDto.setQuantity(shoppingCartQuantity);
                    counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                    counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                    this.cartItemDtos.add(cartItemDto);
                    counter = counter + shoppingCartQuantity;
                }
            }
            shoppingCartQuantity = 1;
        }
        return "pretty:";
    }

    public void checkout() {
        shoppingCartService.saveShoppingCart(this.shoppingCartDto, this.cartItemDtos);
    }

}
