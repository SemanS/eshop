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
    private List<Category> rootCategories;

    @Getter
    @Setter
    private List<Category> categories;

    @Getter
    @Setter
    private Category selectedLazyCategory;

    @Getter
    @Setter
    private String selectedCategory;

    @Getter
    @Setter
    private String selectedRootCategory;

    @Getter
    @Setter
    private String selectedSubCategory;

    @Getter
    @Setter
    private List<String> rootCategoriesString;

    @Getter
    @Setter
    private Category inputRootCategory;

    @Getter
    @Setter
    private Category inputSubCategory;

    @Getter
    @Setter
    private Category inputSelectedRootCategory;

    @PostConstruct
    private void init() {
        rootCategories = categoryService.getRootCategories();
        categories = categoryService.getNonRootCategories();
        inputRootCategory = new Category();
        inputSubCategory = new Category();
        initRootCategory();
    }

    public Category initRootCategory() {
        if (rootCategories == null) {
            return inputSelectedRootCategory = new Category();
        } else {
            return inputSelectedRootCategory = rootCategories.get(0);
        }
    }

    public String onSaveRootCategory() {
        Category category = new Category();
        category.setName(inputRootCategory.getName());
        category.setUrl(inputRootCategory.getUrl());
        category.setBase(true);
        categoryService.saveRootCategory(category);
        return "pretty:dashboard";
    }

    public String onSaveSubCategory() {
        Category category = new Category();
        category.setName(inputSubCategory.getName());
        category.setUrl(inputSubCategory.getUrl());
        category.setParent(categoryService.getCategoryByName(selectedRootCategory));
        category.setBase(false);
        categoryService.saveRootCategory(category);
        return "pretty:dashboard";
    }

    public String onCategoryAssign() {

        Category category = categoryDao.findByName(selectedCategory);
        category.setParent(categoryDao.findByName(selectedRootCategory));
        categoryDao.save(category);

        return "pretty:dashboard";
    }

    public String renderCategory() {
        return categoryDao.findByName(selectedCategory).getParent().getName();
    }

    public String onChangeSelectedRootCategory(String name) {
        inputSelectedRootCategory = categoryDao.findByName(name);
        return inputSelectedRootCategory.getName();
    }


}
