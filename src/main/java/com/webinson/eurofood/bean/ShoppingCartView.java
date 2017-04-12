package com.webinson.eurofood.bean;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.webinson.eurofood.assembler.CartItemAssembler;
import com.webinson.eurofood.assembler.ShoppingCartAssembler;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.service.CartItemService;
import com.webinson.eurofood.service.ItemService;
import com.webinson.eurofood.service.ShoppingCartService;
import lombok.Getter;
import lombok.Setter;
import org.ocpsoft.rewrite.faces.navigate.Navigate;
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
import java.util.*;

/**
 * Created by Slavo on 1/6/2017.
 */
@Scope("session")
@Component
public class ShoppingCartView {

    @Getter
    @Setter
    private int counter = 0;

    @Setter
    private double counterNetto = 0.00;

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
    /*@Getter
    @Setter
    private float total;*/
    @Getter
    @Setter
    private String itemUrl;
    @Getter
    @Setter
    ItemDto itemDto;
    @Getter
    @Setter
    private double totalPriceBrutto;

    @Autowired
    ItemService itemService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartAssembler shoppingCartAssembler;

    @Autowired
    CartItemAssembler cartItemAssembler;

    @Autowired
    CartItemService cartItemService;

    public String loadItem() throws IOException {

        if (itemUrl != null) {
            this.itemDto = itemService.getItemByUrl(itemUrl);
            return itemDto.getUrl();
        }

        // Add a message here, "The item {..} could not be found."
        return "";
    }

    @PostConstruct
    public void init() {
        shoppingCartDto = new ShoppingCartDto();
        cartItemDtos = new HashSet<>();
    }

    public void onRestartCounter() {
        this.counter = 0;
        this.cartItemDtos = new HashSet<>();
        this.counterNetto = 0;
        this.counterBrutto = 0;
        /*return "pretty:";*/
    }

    public void addItemToCart(ItemDto itemDto) throws IOException {

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

                if (itemDto.isDiscount()) {
                    counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNettoDiscount();
                    counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBruttoDiscount();
                }
                if (!itemDto.isDiscount()) {
                    counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                    counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                }

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
                if (itemDto.isDiscount()) {
                    counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNettoDiscount();
                    counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBruttoDiscount();
                }
                if (!itemDto.isDiscount()) {
                    counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                    counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                }
                counter = counter + shoppingCartQuantity;


            } else if (cartItemDtos.size() != 1) {
                counter = counter + shoppingCartQuantity;
                if (itemDto.isDiscount()) {
                    counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNettoDiscount();
                    counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBruttoDiscount();
                }
                if (!itemDto.isDiscount()) {
                    counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                    counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                }
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
                    if (itemDto.isDiscount()) {
                        counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNettoDiscount();
                        counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBruttoDiscount();
                    }
                    if (!itemDto.isDiscount()) {
                        counterNetto = counterNetto + shoppingCartQuantity * itemDto.getPriceNetto();
                        counterBrutto = counterBrutto + shoppingCartQuantity * itemDto.getPriceBrutto();
                    }
                    this.cartItemDtos.add(cartItemDto);
                    counter = counter + shoppingCartQuantity;
                }
            }
            shoppingCartQuantity = 1;
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
    }

    public String checkout() {
        shoppingCartService.saveShoppingCart(this.shoppingCartDto, this.cartItemDtos);
        return "index.xhtml?faces-redirect=true";
    }

    public double countTotalPriceNetto(CartItemDto cartItemDto) {

        counterNetto = 0.0;
        for (CartItemDto cItemDto : cartItemDtos) {
            if (cartItemDto != null && cartItemDto.getItemDto().getHeader() == cItemDto.getItemDto().getHeader()) {
                counterNetto = counterNetto + cItemDto.getItemDto().getPriceNetto() * cartItemDto.getQuantity();
            } else {
                counterNetto = counterNetto + cItemDto.getItemDto().getPriceNetto() * cItemDto.getQuantity();
            }

        }
        return counterNetto;
    }

    public double countTotalPriceBrutto() {


        for (CartItemDto cItemDto : cartItemDtos) {
            totalPriceBrutto = cItemDto.getItemDto().getPriceBrutto() * cItemDto.getQuantity();
        }
        return totalPriceBrutto;
    }


    public void onDeleteItem(CartItemDto cartItemDto) throws IOException {
        String redirectOption;
        for (CartItemDto cItemDto : cartItemDtos) {
            if (cartItemDto.getItemDto().getHeader().equals(cItemDto.getItemDto().getHeader())) {
                cartItemDtos.remove(cItemDto);
            }
        }
        if (cartItemDtos.size() == 0) {
            redirectOption = "/index.xhtml";
            onRestartCounter();

        } else {
            redirectOption = "/checkoutCart.xhtml";
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect(redirectOption);
    }

    public double getCounterBrutto() {
        return (Math.ceil(counterBrutto * 100)) / 100;
    }

    public double getCounterNetto() {
        return (Math.ceil(counterNetto * 100)) / 100;
    }
}
