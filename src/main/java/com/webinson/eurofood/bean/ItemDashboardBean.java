package com.webinson.eurofood.bean;

import com.webinson.eurofood.assembler.ItemAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ItemService;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Slavo on 1/17/2017.
 */
@Component
@ViewScoped
public class ItemDashboardBean {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryService categoryService;

    @Getter
    @Setter
    private Category selectedCategory;

    @Getter
    @Setter
    private List<Category> categories;

    private ItemLazyDataModel items;

    @Getter
    @Setter
    private Item selectedItem;

    @PostConstruct
    private void init() {
        categories = categoryDao.findAll();
        this.items = new ItemLazyDataModel(itemService);
        selectedItem = new Item();
    }

    public ItemLazyDataModel getItems() {
        return items;
    }

    public void onRowSelect(SelectEvent event) {
        selectedItem = itemDao.findById(((Item) event.getObject()).getId());
    }

    public void onSaveItem() {
        itemDao.save(selectedItem);
    }

    public void onNewItem() {
        this.selectedItem = new Item();
    }


}
