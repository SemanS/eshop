package com.webinson.eurofood.bean;

import com.webinson.eurofood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Created by Slavo on 1/17/2017.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UserBean {

    @Autowired
    private UserService userService;

    private UserLazyDataModel users;

    @PostConstruct
    private void init() {
        this.users = new UserLazyDataModel(userService);
    }

    public UserLazyDataModel getUsers() {
        return users;
    }
}