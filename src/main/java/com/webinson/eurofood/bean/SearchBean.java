package com.webinson.eurofood.bean;

import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Slavo on 2/17/2017.
 */
@Component
@RequestScoped
public class SearchBean {

    @Autowired
    ItemService itemService;

    @Getter
    @Setter
    private String selectedItem;

    public List<String> completeText(String query) {
        return itemService.queryByName(query);
    }

    public void onSearch() throws IOException {

        if (selectedItem != "") {
            if (itemService.itemExist(selectedItem)) {
                ItemDto itemDto = itemService.getItemByName(this.selectedItem);
                if (itemDto != null) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/store/" + itemDto.getUrl());
                }
            }
        }
    }

}
