package com.webinson.zuzka.bean;

import com.webinson.zuzka.dto.ItemDto;
import com.webinson.zuzka.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Slavo on 10/18/2016.
 */
@Component
@ViewScoped
public class DashboardView {

    @Getter
    @Setter
    public String selectedCard;

    @Autowired
    ItemService itemService;

    @Getter
    @Setter
    public List<ItemDto> cards;

    @PostConstruct
    public void init() {

        cards = itemService.getAllItems();

    }

    public void onEditCard(ItemDto itemDto) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/" + itemDto.getUrl());
        System.out.println(itemDto.getUrl());
    }

}
