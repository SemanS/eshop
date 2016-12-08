package com.webinson.eurofood.bean;

import com.google.common.collect.Lists;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.service.UserService;
import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.impl.EmailImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Slavo on 12/7/2016.
 */
@Component
public class UserBean {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Autowired
    private UserService userService;

    public String updateData() {
        UserDto userDto = new UserDto();
        userDto.setName(username);
        userDto.setPassword(password);
        userService.registerNewUserAccount(userDto);
        return "confirmation?faces-redirect=true";
    }
}
