package com.webinson.eurofood.dto;

import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;

import java.util.List;

/**
 * Created by Slavo on 12/3/2016.
 */
@Data
public class CategoryDto {
    private Long id;
    private String name;
    private CategoryDto parent;
    private List<CategoryDto> children;
    private String image;
    private boolean base;
    private String url;
    private String imageDescription;
    private String subcategoryImageDescription;
}
