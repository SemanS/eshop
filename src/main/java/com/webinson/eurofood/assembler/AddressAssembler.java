package com.webinson.eurofood.assembler;

import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.entity.Address;
import com.webinson.eurofood.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Slavo on 1/13/2017.
 */
@Component
public class AddressAssembler {

    public AddressDto toDto(Address model) {
        AddressDto dto = new AddressDto();
        dto.setLastName(model.getLastName());
        dto.setFirstName(model.getFirstName());
        dto.setPostalCode(model.getPostalCode());
        dto.setCity(model.getCity());
        dto.setStreet(model.getStreet());
        return dto;
    }

    public AddressDto convertToDto(Address model, AddressDto dto) {
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setPostalCode(model.getPostalCode());
        dto.setStreet(model.getStreet());
        dto.setCity(model.getCity());
        return dto;
    }

    public Address convertToModel(AddressDto dto, Address model) {
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setPostalCode(dto.getPostalCode());
        model.setStreet(dto.getStreet());
        model.setCity(dto.getCity());
        return model;
    }

    public Address toModel(AddressDto dto) {
        Address model = new Address();
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setPostalCode(dto.getPostalCode());
        model.setStreet(dto.getStreet());
        model.setCity(dto.getCity());
        return model;
    }

    public List<AddressDto> toDtos(final Collection<Address> models) {
        final List<AddressDto> dtos = new ArrayList<AddressDto>();
        if (isNotEmpty(models)) {
            for (final Address address : models) {
                dtos.add(convertToDto(address, new AddressDto()));
            }
        }
        return dtos;
    }

    public List<Address> toModels(final Collection<AddressDto> dtos) {
        final List<Address> models = new ArrayList<Address>();
        if (isNotEmpty(dtos)) {
            for (final AddressDto addressDto : dtos) {
                models.add(convertToModel(addressDto, new Address()));
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
