package com.webinson.eurofood.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Slavo on 12/3/2016.
 */
@Data
public class CategoryDto {
    private Long id;
    private String name;
    private CategoryDto parent;
}
