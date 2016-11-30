package com.webinson.zuzka.bean;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
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
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slavo on 10/4/2016.
 */
@Component
@ViewScoped
@URLBeanName("itemView")
@URLMappings(mappings = {
        @URLMapping(
                id = "home",
                pattern = "/",
                viewId = "/index.xhtml"),
        @URLMapping(
                id = "login",
                pattern = "/login",
                viewId = "/login.xhtml"),
        @URLMapping(
                id = "category",
                pattern = "/category/#{ selectedCategory: itemView.selectedCategory}/#{ selectedItem : itemView.selectedItem}",
                viewId = "/category.xhtml"),
        @URLMapping(
                id = "items",
                pattern = "/items",
                viewId = "/items.xhtml"),
        @URLMapping(
                id = "item-detail",
                pattern = "/items/#{ itemUrl : itemView.itemUrl }",
                viewId = "/itemDetail.xhtml"),
        @URLMapping(
                id = "dashboard",
                pattern = "/dashboard",
                viewId = "/dashboard.xhtml"),
        @URLMapping(
                id = "dashboard-detail",
                pattern = "/dashboard/#{ itemUrl : itemView.itemUrl}",
                viewId = "/dashboarditemdetail.xhtml")
})
public class ItemView implements Serializable {

    @Autowired
    ItemService itemService;

    @Getter
    @Setter
    private String selectedCategory;

    @Getter
    @Setter
    private ItemDto selectedItem;

    @Getter
    @Setter
    private List<ItemDto> items;

    @Getter
    @Setter
    private String itemUrl;

    @Getter
    @Setter
    private String selectedUser;


    @URLAction
    public String loadItem() throws IOException {

        if (itemUrl != null) {
            this.selectedItem = itemService.getItemByUrl(itemUrl);
            return selectedItem.getUrl();
        }

        // Add a message here, "The item {..} could not be found."
        return "";
    }

    @PostConstruct
    public void init() {

        items = itemService.getAllItems();
    }

    public void onItemDetail(ItemDto itemDto) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/items/" + itemDto.getUrl());
    }

    public void onDashboardItemDetail(ItemDto itemDto) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard/" + itemDto.getUrl());
    }

    public void saveText() {

        System.out.println(itemUrl);
        //System.out.println(selectedCard);
        /*String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];

        itemService.saveItemByUrl(resultUrl, text);*/
    }

}
