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
import javax.servlet.http.Part;
import java.util.Base64;
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
    private String selectedRootCategory;

    @Getter
    @Setter
    private String selectedCategory;

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

    @Getter
    @Setter
    private Category inputSelectedSubCategory;

    @Getter
    @Setter
    private String selectedRootCategoryAssign;

    @Getter
    @Setter
    private String selectedRootCategoryAdd;

    @Getter
    @Setter
    private Part file;

    @PostConstruct
    private void init() {
        rootCategories = categoryService.getRootCategories();
        categories = categoryService.getNonRootCategories();
        inputRootCategory = new Category();
        inputSubCategory = new Category();
        initRootCategory();
        initSubCategory();
    }

    public Category initRootCategory() {
        if (rootCategories == null) {
            return inputSelectedRootCategory = new Category();
        } else {
            return inputSelectedRootCategory = rootCategories.get(0);
        }
    }

    public Category initSubCategory() {
        if (categories == null) {
            return inputSelectedSubCategory = new Category();
        } else {
            return inputSelectedSubCategory = categories.get(0);
        }
    }

    public String onChangeRootCategory() {
        Category category;
        category = categoryDao.findByName(selectedRootCategory);
        category.setName(inputSelectedRootCategory.getName());
        category.setUrl(inputSelectedRootCategory.getUrl());
        categoryDao.save(category);
        return "pretty:dashboard";
    }

    public String onChangeSubCategory() {
        Category category;
        category = categoryDao.findByName(selectedSubCategory);
        category.setName(inputSelectedSubCategory.getName());
        category.setUrl(inputSelectedSubCategory.getUrl());
        categoryDao.save(category);
        return "pretty:dashboard";
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

    public String onSubCategoryAssign() {
        Category category = categoryDao.findByName(selectedSubCategory);
        category.setParent(categoryDao.findByName(selectedRootCategoryAssign));
        categoryDao.save(category);
        return "pretty:dashboard";
    }

    public String onSubCategoryAdd() {
        Category category = new Category();
        category.setName(inputSubCategory.getName());
        category.setUrl(inputSubCategory.getUrl());
        category.setParent(categoryDao.findByName(selectedRootCategoryAdd));
        categoryDao.save(category);
        return "pretty:dashboard";
    }

    public String onChangeSelectedRootCategory(String name) {
        inputSelectedRootCategory = categoryDao.findByName(name);
        return inputSelectedRootCategory.getName();
    }

    public String onChangeSelectedSubCategory(String name) {
        inputSelectedSubCategory = categoryDao.findByName(name);
        return inputSelectedSubCategory.getName();
    }

    public String onChangeAssignedCategory(String name) {
        Category category = categoryDao.findByName(name);
        selectedRootCategoryAssign = category.getParent().getName();
        return selectedRootCategoryAssign;
    }


    public String getBaseImage(Category category) {
        if(category.getImage() == null) {
            return "";
        }
        String newImage = "";
        newImage = Base64.getEncoder().encodeToString(category.getImage());
        return newImage;
    }
}
