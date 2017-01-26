package com.webinson.eurofood.bean;

import com.webinson.eurofood.assembler.ItemAssembler;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dao.ShoppingCartDao;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.service.ShoppingCartService;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavo on 1/24/2017.
 */
@Component
@ViewScoped
public class ShoppingCartDashboardBean {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ItemAssembler itemAssembler;

    @Getter
    @Setter
    private ShoppingCart selectedShoppingCart;

    @Getter
    @Setter
    private Item selectedItem;

    @Getter
    private ShoppingCartLazyDataModel shoppingCarts;

    @Getter
    @Setter
    private List<ItemDto> itemList;

    @Getter
    @Setter
    private ShoppingCart shoppingCartBool;

    @PostConstruct
    private void init() {
        this.shoppingCarts = new ShoppingCartLazyDataModel(shoppingCartService);
        shoppingCartBool = new ShoppingCart();
    }

    public void onRowSelect(SelectEvent event) {

        selectedShoppingCart = shoppingCartDao.findById(((ShoppingCart) event.getObject()).getId());
        if (shoppingCartBool.getId() != selectedShoppingCart.getId()) {
            itemList = new ArrayList<>();
            shoppingCartBool = selectedShoppingCart;
            for (CartItem cartItem : selectedShoppingCart.getCartItems()) {
                ItemDto itemDto = itemAssembler.toDto(itemDao.findById(cartItem.getItemId()));
                itemDto.setQuantity(cartItem.getQuantity());
                itemList.add(itemDto);
            }
        }
    }

    public void onRowUnselect(SelectEvent event) {
        selectedShoppingCart = null;
        itemList = null;
    }

    public void onExpedite() {
        selectedShoppingCart.setProcessed(true);
        shoppingCartDao.save(selectedShoppingCart);
    }

}
