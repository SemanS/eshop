package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Slavo on 12/1/2016.
 */
@Component
@ApplicationScope
public class MenuBean {

    @Getter
    @Setter
    private TreeNode rootNode;

    @Getter
    @Setter
    private TreeModel<CategoryDto> treeModel;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryDao categoryDao;

    @PostConstruct
    public void init() {
        treeModel = categoryService.createModel();
    }

    /*public DefaultStreamedContent getImage() throws IOException {

        Category category = categoryDao.findById(Long.valueOf(4));
        return new DefaultStreamedContent(new ByteArrayInputStream(category.getImage()));

    }*/

}