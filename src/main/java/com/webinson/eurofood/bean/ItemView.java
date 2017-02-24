package com.webinson.eurofood.bean;

import com.ocpsoft.pretty.faces.annotation.*;
import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.assembler.ItemAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dto.CartItemDto;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.dto.ShoppingCartDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ItemService;
import lombok.*;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.Parameter;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Slavo on 10/4/2016.
 */
@Component
@ELBeanName(value = "itemView")
@ViewScoped
@Join(path = "/store/{itemUrl}", to="/itemDetail.xhtml")
/*@URLMappings(mappings = {
        @URLMapping(
                id = "detailItem",
                pattern = "/store/#{itemUrl: itemView.itemUrl}",
                viewId = "/itemDetail.xhtml"),
        @URLMapping(
                id = "detailCategory",
                pattern = "/category/#{ categoryUrl: itemView.categoryUrl}",
                viewId = "/eshop.xhtml"),
})*/
public class ItemView implements Serializable {

    /*@Autowired
    CategoryDao categoryDao;*/

    @Autowired
    ItemDao itemDao;

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryAssembler categoryAssembler;

    @Autowired
    ItemAssembler itemAssembler;

    /*@Autowired
    CategoryService categoryService;*/

    /*@Getter
    @Setter
    private CategoryDto selectedCategory;*/

    @Getter
    @Setter
    private ItemDto selectedItem;

    /*@Getter
    @Setter
    private String categoryUrl;*/

    @Getter
    @Setter
    private List<ItemDto> items;

    @Getter
    @Setter
    private List<ItemDto> promotedItems;

    @Parameter
    @Deferred
    @Getter
    @Setter
    private String itemUrl;

    @Getter
    @Setter
    private String selectedUser;

    /*@Getter
    @Setter
    private String selectedCategoryImage;*/

    /*@URLAction
    public String loadCategory() throws IOException {

        if (categoryUrl != null) {
            this.selectedCategory = categoryService.getCategoryByUrl(categoryUrl);
            return selectedCategory.getUrl();
        }

        // Add a message here, "The item {..} could not be found."
        return "";
    }*/

    @RequestAction
    @Deferred
    @IgnorePostback
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
        promotedItems = itemService.getAllPromotedItems();

    }

    /*public String onImageDescriptionChange() {
        selectedCategoryImage = categoryAssembler.toDto(categoryDao.findById(this.selectedCategory.getId())).getImageDescription();
        return selectedCategoryImage;
    }*/

    /*public List<ItemDto> allItemsByCategory() {
        return itemService.getItemsByCategory(this.selectedCategory);
    }*/

    /*public void onCategory(Category category) throws IOException {
        selectedCategory = categoryAssembler.toDto(categoryDao.findById(category.getId()));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/category/" + category.getUrl());
    }*/

    public void onItem(ItemDto itemDto) throws IOException {
        itemDto.setQuantity(1);
        selectedItem = itemDto;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/store/" + itemDto.getUrl());
    }

}
