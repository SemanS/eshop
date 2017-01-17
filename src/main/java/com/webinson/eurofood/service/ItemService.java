package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Slavo on 10/17/2016.
 */
public interface ItemService {

    public List<ItemDto> getAllItems();

    public String getTextOfItemByUrl(String url);

    public ItemDto getItemByUrl(String url);

    public void saveItemByUrl(String url, String text);

    public List<ItemDto> getItemsByCategory(CategoryDto categoryDto);

    Page<User> findByFilter(Map<String, String> filters, Pageable pageable);

}
