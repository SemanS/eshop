package com.webinson.eurofood.bean;

import com.webinson.eurofood.dto.ItemDto;
import org.primefaces.model.LazyDataModel;

/**
 * Created by Slavo on 1/17/2017.
 */
public class LazyItemDataModel extends LazyDataModel<ItemDto> {

    @Override
    public ItemDto getRowData(String rowKey) {
        for(ItemDto itemDto : datasource) {
            if(car.getId().equals(rowKey))
                return car;
        }

        return null;
    }

}
