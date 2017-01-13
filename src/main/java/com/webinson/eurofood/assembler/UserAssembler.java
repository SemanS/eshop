package com.webinson.eurofood.assembler;

import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by Slavo on 1/13/2017.
 */
@Component
public class UserAssembler {

    public UserDto toDto(User model) {
        UserDto userDto = new UserDto();
        return userDto;
    }

}
