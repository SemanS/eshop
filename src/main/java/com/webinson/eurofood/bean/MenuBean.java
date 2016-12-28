package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

    @PostConstruct
    public void init() {

        treeModel = categoryService.createModel();
        //rootNode = categoryService.createRoot();
    }

}