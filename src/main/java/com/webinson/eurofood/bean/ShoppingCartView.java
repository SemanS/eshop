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
    private int shoppingCartQuantity;

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
        shoppingCartDto = new ShoppingCartDto();
        cartItemDtos = new HashSet<>();
    }

    public void onRestartCounter() {
        this.counter = 0;
        this.cartItemDtos = new HashSet<>();
    }

    public void addItemToCart(ItemDto itemDto) {

        CartItemDto cartItemDto = new CartItemDto();
        boolean isInCart = false;
        boolean isInSecondCart = false;
        int counterOfProduct = 0;

        if (cartItemDtos.size() == 0) {
            cartItemDto.setItemId(itemDto.getId());
            cartItemDto.setQuantity(itemDto.getQuantity());
            counter = counter + itemDto.getQuantity();
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
                    counterOfProduct = cartI.getQuantity() + itemDto.getQuantity();
                    cartI.setQuantity(counterOfProduct);
                }
            }
            counter = counter + itemDto.getQuantity();

        } else if (cartItemDtos.size() != 1) {
            counter = counter + itemDto.getQuantity();
            cartItemDto.setItemId(itemDto.getId());
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
                cartItemDto.setQuantity(itemDto.getQuantity());
                this.cartItemDtos.add(cartItemDto);
                counter = counter + itemDto.getQuantity();
            }
        }
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
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
