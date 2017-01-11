package com.webinson.eurofood.assembler;

import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.Item;
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
        dto.setId(model.getId());
        dto.setHeader(model.getHeader());
        dto.setText(model.getText());
        dto.setDate(model.getDate());
        dto.setUrl(model.getUrl());
        dto.setCategory(model.getCategory());
        dto.setInternalNumber(model.getInternalNumber());
        dto.setPriceNetto(model.getPriceNetto());
        dto.setPriceBrutto(model.getPriceBrutto());
        dto.setEanNumber(model.getEanNumber());
        dto.setProducer(model.getProducer());
        dto.setPiecesInCarton(model.getPiecesInCarton());
        dto.setCartonInPalette(model.getCartonInPalette());
        dto.setPiecesInPalette(model.getPiecesInPalette());
        dto.setDiscount(model.isDiscount());
        return dto;
    }

    public ItemDto toDto(Item model) {
        ItemDto dto = new ItemDto();
        dto.setId(model.getId());
        dto.setHeader(model.getHeader());
        dto.setText(model.getText());
        dto.setDate(model.getDate());
        dto.setUrl(model.getUrl());
        dto.setCategory(model.getCategory());
        dto.setInternalNumber(model.getInternalNumber());
        dto.setPriceNetto(model.getPriceNetto());
        dto.setPriceBrutto(model.getPriceBrutto());
        dto.setEanNumber(model.getEanNumber());
        dto.setProducer(model.getProducer());
        dto.setPiecesInCarton(model.getPiecesInCarton());
        dto.setCartonInPalette(model.getCartonInPalette());
        dto.setPiecesInPalette(model.getPiecesInPalette());
        dto.setDiscount(model.isDiscount());
        return dto;
    }

    public Item toModel(ItemDto dto) {
        Item model = new Item();
        model.setHeader(dto.getHeader());
        model.setText(dto.getText());
        model.setDate(dto.getDate());
        model.setUrl(dto.getUrl());
        model.setCategory(dto.getCategory());
        model.setInternalNumber(dto.getInternalNumber());
        model.setPriceNetto(dto.getPriceNetto());
        model.setPriceBrutto(dto.getPriceBrutto());
        model.setEanNumber(dto.getEanNumber());
        model.setProducer(dto.getProducer());
        model.setPiecesInCarton(dto.getPiecesInCarton());
        model.setCartonInPalette(dto.getCartonInPalette());
        model.setPiecesInPalette(dto.getPiecesInPalette());
        model.setDiscount(dto.isDiscount());
        return model;
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
