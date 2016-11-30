package com.webinson.zuzka.assembler;

import com.webinson.zuzka.dto.ItemDto;
import com.webinson.zuzka.entity.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Component
public class ItemAssembler {


    public ItemDto convertToDto(Item model, ItemDto dto) {
        dto.setHeader(model.getHeader());
        dto.setText(model.getText());
        dto.setDate(model.getDate());
        dto.setUrl(model.getUrl());
        dto.setCategory(model.getCategory());
        return dto;
    }

    public ItemDto toDto(Item model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setHeader(model.getHeader());
        itemDto.setText(model.getText());
        itemDto.setDate(model.getDate());
        itemDto.setUrl(model.getUrl());
        itemDto.setCategory(model.getCategory());
        return itemDto;
    }


    public List<ItemDto> toDtos(final Collection<Item> models) {
        final List<ItemDto> dtos = new ArrayList<>();
        if (isNotEmpty(models)) {
            for (final Item item : models) {
                dtos.add(convertToDto(item, new ItemDto()));
            }
        }
        return dtos;
    }

    public boolean isNotEmpty(final Collection<?> col) {
        return !isEmpty(col);
    }

    public boolean isEmpty(final Collection<?> col) {
        return col == null || col.isEmpty();
    }

}
