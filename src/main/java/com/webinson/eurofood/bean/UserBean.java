package com.webinson.eurofood.bean;

import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
