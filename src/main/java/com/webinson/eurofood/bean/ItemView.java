package com.webinson.eurofood.bean;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.assembler.ItemAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ItemService;
import lombok.*;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slavo on 10/4/2016.
 */
@Component
@Scope("session")
@URLBeanName("itemView")
@URLMappings(mappings = {
        @URLMapping(
                id = "index",
                pattern = "/",
                viewId = "/index.xhtml"),
        @URLMapping(
                id = "itemDetail",
                pattern = "/store/#{ itemUrl : itemView.itemUrl}",
                viewId = "/itemDetail.xhtml"),
        @URLMapping(
                id = "category",
                pattern = "/category/#{ categoryUrl: itemView.categoryUrl}",
                viewId = "/eshop.xhtml"),
})
public class ItemView implements Serializable {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryAssembler categoryAssembler;

    @Autowired
    ItemAssembler itemAssembler;

    @Autowired
    CategoryService categoryService;

    @Getter
    @Setter
    private CategoryDto selectedCategory;

    @Getter
    @Setter
    private ItemDto selectedItem;

    @Getter
    @Setter
    private String categoryUrl;

    @Getter
    @Setter
    private List<ItemDto> items;

    @Getter
    @Setter
    private String itemUrl;

    @Getter
    @Setter
    private String selectedUser;

    @Getter
    @Setter
    private String selectedCategoryImage;

    @URLAction
    public String loadCategory() throws IOException {

        if (categoryUrl != null) {
            this.selectedCategory = categoryService.getCategoryByUrl(categoryUrl);
            return selectedCategory.getUrl();
        }

        // Add a message here, "The item {..} could not be found."
        return "";
    }
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
        //selectedCategory = categoryAssembler.toDto(categoryDao.findById(1L));
    }

    public String onImageDescriptionChange() {
        selectedCategoryImage = categoryAssembler.toDto(categoryDao.findById(this.selectedCategory.getId())).getImageDescription();
        return selectedCategoryImage;
    }

    public List<ItemDto> allItemsByCategory() {
        return itemService.getItemsByCategory(this.selectedCategory);
    }

    public void onCategory(CategoryDto categoryDto) throws IOException {
        selectedCategory = categoryAssembler.toDto(categoryDao.findById(categoryDto.getId()));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/category/" + categoryDto.getUrl());
    }

    public void onItem(ItemDto itemDto) throws IOException {
        itemDto.setQuantity(1);
        selectedItem = itemDto;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/store/" + itemDto.getUrl());
        System.out.println("daco");
    }

}
