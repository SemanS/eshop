package com.webinson.zuzka.service;

import com.webinson.zuzka.dto.ItemDto;
import com.webinson.zuzka.dto.ItemDto;

import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
public interface ItemService {

    public List<ItemDto> getAllItems();

    public String getTextOfItemByUrl(String url);

    public ItemDto getItemByUrl(String url);

    public void saveItemByUrl(String url, String text);

}
