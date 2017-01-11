package com.webinson.eurofood.assembler;

import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

/**
 * Created by Slavo on 12/3/2016.
 */
@Component
public class CategoryAssembler {

    public CategoryDto convertToDto(Category model, CategoryDto dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setImage(Base64.getEncoder().encodeToString(model.getImage()));
        dto.setBase(model.isBase());
        dto.setUrl(model.getUrl());
        dto.setImageDescription(Base64.getEncoder().encodeToString(model.getImageDescription()));
        return dto;
    }

    public CategoryDto toDto(Category model) {
        CategoryDto dto = new CategoryDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setImage(Base64.getEncoder().encodeToString(model.getImage()));
        dto.setBase(model.isBase());
        dto.setUrl(model.getUrl());
        dto.setImageDescription(Base64.getEncoder().encodeToString(model.getImageDescription()));
        return dto;
    }

    public List<CategoryDto> toDtos(final Collection<Category> models) {
        final List<CategoryDto> dtos = new ArrayList<>();
        if (isNotEmpty(models)) {
            for (final Category category : models) {
                dtos.add(convertToDto(category, new CategoryDto()));
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
