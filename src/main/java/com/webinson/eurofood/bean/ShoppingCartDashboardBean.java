package com.webinson.eurofood.bean;

import com.webinson.eurofood.service.ShoppingCartService;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

/**
 * Created by Slavo on 1/24/2017.
 */
@Component
@ViewScoped
public class ShoppingCartDashboardBean {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Getter
    private ShoppingCartLazyDataModel shoppingCarts;

    @PostConstruct
    private void init() {
        this.shoppingCarts = new ShoppingCartLazyDataModel(shoppingCartService);
    }

}
