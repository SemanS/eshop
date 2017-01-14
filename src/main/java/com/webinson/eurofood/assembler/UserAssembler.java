package com.webinson.eurofood.assembler;

import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.Address;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Slavo on 1/13/2017.
 */
@Component
public class UserAssembler {

    @Autowired
    private AddressAssembler addressAssembler;

    public UserDto convertToDto(User model, UserDto dto) {
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setAddressDtos(addressAssembler.toDtos(model.getAddresses()));
        return dto;
    }

    public User convertToModel(UserDto dto, User model) {
        model.setPhoneNumber(dto.getPhoneNumber());
        model.setAddresses(addressAssembler.toModels(dto.getAddressDtos()));
        return model;
    }

    public UserDto toDto(User model) {
        UserDto dto = new UserDto();
        dto.setAddressDtos(addressAssembler.toDtos(model.getAddresses()));
        dto.setPhoneNumber(model.getPhoneNumber());
        return dto;
    }

    public User toModel(UserDto userDto) {
        User model = new User();
        model.setPhoneNumber(userDto.getPhoneNumber());
        model.setAddresses(addressAssembler.toModels(userDto.getAddressDtos()));
        return model;
    }

    public List<UserDto> toDtos(final Collection<User> models) {
        final List<UserDto> dtos = new ArrayList<UserDto>();
        if (isNotEmpty(models)) {
            for (final User user : models) {
                dtos.add(convertToDto(user, new UserDto()));
            }
        }
        return dtos;
    }

    public List<User> toModels(final Collection<UserDto> dtos) {
        final List<User> models = new ArrayList<User>();
        if (isNotEmpty(dtos)) {
            for (final UserDto userDto : dtos) {
                models.add(convertToModel(userDto, new User()));
            }
        }
        return models;
    }

    public boolean isNotEmpty(final Collection<?> col) {
        return !isEmpty(col);
    }

    public boolean isEmpty(final Collection<?> col) {
        return col == null || col.isEmpty();
    }

}
