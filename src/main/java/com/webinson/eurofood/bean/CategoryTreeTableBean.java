package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.TreeNodeComparator;
import org.primefaces.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Slavo on 1/21/2017.
 */
@Component
@ViewScoped
public class CategoryTreeTableBean {

    @Getter
    @Setter
    private List<Category> categories;

    /*@Getter
    @Setter
    private TreeNode selectedNode = new DefaultTreeNode();

    @Getter
    @Setter
    private TreeNode expandedNodeTree = new DefaultTreeNode();*/


    @Autowired
    CategoryService categoryService;

    @PostConstruct
    public void init() {

        categories = categoryService.getRootCategories();
    }

    public List<Category> getChildrenOfCategory(Long id) {
        List<Category> categoriesList;
        categoriesList = categoryService.findChildrenOfCategory(id);
        return categoriesList;
    }




}
