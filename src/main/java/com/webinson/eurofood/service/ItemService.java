package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Slavo on 10/17/2016.
 */
public interface ItemService {

    List<ItemDto> getAllItems();

    String getTextOfItemByUrl(String url);

    ItemDto getItemByUrl(String url);

    void saveItemByUrl(String url, String text);

    List<ItemDto> getItemsByCategory(CategoryDto categoryDto);

    Page<Item> findByFilter(Map<String, String> filters, Pageable pageable);

    ItemDto getItemById(Long id);

    List<ItemDto> getAllPromotedItems();

    List<String> queryByName(String name);

    ItemDto getItemByName(String name);

    boolean itemExist(String name);

    List<ItemDto> getBestFiveSellingProducts();
}
