package com.webinson.zuzka.service.impl;

import com.webinson.zuzka.assembler.ItemAssembler;
import com.webinson.zuzka.dao.ItemDao;
import com.webinson.zuzka.dto.ItemDto;
import com.webinson.zuzka.entity.Item;
import com.webinson.zuzka.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    ItemAssembler itemAssembler;

    public List<ItemDto> getAllItems() {

        List<ItemDto> items = new ArrayList<ItemDto>();
        items = itemAssembler.toDtos(itemDao.findAll());
        return items;

    }

    @Override
    public String getTextOfItemByUrl(String url) {
        return itemDao.findByUrl(url).getText();
    }

    @Override
    public ItemDto getItemByUrl(String url) {
        ItemDto itemDto = new ItemDto();
        itemDto = itemAssembler.toDto(itemDao.findByUrl(url));
        return itemDto;
    }

    @Override
    public void saveItemByUrl(String url, String text) {
        Item item = itemDao.findByUrl(url);
        item.setText(text);
        itemDao.save(item);
    }

}
