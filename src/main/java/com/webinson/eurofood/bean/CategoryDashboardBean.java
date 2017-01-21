package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Slavo on 1/17/2017.
 */
@Component
@ViewScoped
public class CategoryDashboardBean {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Getter
    @Setter
    private List<Category> categories;

    @Getter
    @Setter
    private List<Category> rootCategories;

    @Getter
    @Setter
    private List<String> categoriesString;

    private ItemLazyDataModel items;

    @Getter
    @Setter
    private String selectedCategory;

    @Getter
    @Setter
    private String selectedRootCategory;

    @Getter
    @Setter
    private List<String> rootCategoriesString;

    @PostConstruct
    private void init() {
        categories = categoryService.getNonRootCategories();
        rootCategories = categoryService.getRootCategories();
        rootCategoriesString =
        categoriesString = categoryService.getStringCategories();
    }

    public ItemLazyDataModel getItems() {
        return items;
    }

    public void onRowSelect(SelectEvent event) {

        /*selectedItem = itemDao.findById(((Item) event.getObject()).getId());
        selectedCategory = selectedItem.getCategory().getName();*/

    }

    public String onSaveItem() {

        /*selectedItem.setCategory(categoryDao.findByName(selectedCategory));
        itemDao.save(selectedItem);
        return "pretty:dashboard";*/
        return "pretty:dashboard";
    }

    public String onDeleteItem() {

        /*itemDao.delete(selectedItem);
        return "pretty:dashboard";*/
        return "pretty:dashboard";
    }

    public void onNewItem() {

        /*this.selectedItem = new Item();*/
    }


}
