package com.webinson.eurofood.bean;

import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

/**
 * Created by Slavo on 1/21/2017.
 */
@Component
@ViewScoped
public class CategoryTreeTableBean {

    @Getter
    @Setter
    private TreeNode rooCategories;

    @Autowired
    CategoryService categoryService;

    @PostConstruct
    public void init() {
        rooCategories = categoryService.buildCategories();
    }

}
