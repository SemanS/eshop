package com.webinson.zuzka.bean;

import com.ocpsoft.pretty.PrettyContext;
import com.webinson.zuzka.dto.ItemDto;
import com.webinson.zuzka.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by Slavo on 10/18/2016.
 */
@Component
@ViewScoped
public class ItemDetailView implements Serializable {

    @Getter
    @Setter
    /*@ManagedProperty(value = "#{param.selectedCard}")*/
    public String selectedCard;

    @Getter
    @Setter
/*    @ManagedProperty(value = "#{param.selectedCategory}")*/
    public String selectedCategory;

    @Getter
    @Setter
    private ItemDto itemDto;

    @Autowired
    ItemService itemService;

    @PostConstruct
    public void init() {

        //cardDto = itemService.getItemByUrl(showText());
       /* selectedCard = PrettyContext.getCurrentInstance().getRequestURL().toURL().substring(10);*/
        //selectedCardDashboard = PrettyContext.getCurrentInstance().getRequestURL().toURL().substring(11);
    }

    public String showText() {

        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        return resultUrl;
    }

}
