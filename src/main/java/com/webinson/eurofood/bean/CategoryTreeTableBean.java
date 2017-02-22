package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.TreeNodeComparator;
import org.primefaces.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Slavo on 1/21/2017.
 */
@Component
@ViewScoped
public class CategoryTreeTableBean {

    @Getter
    @Setter
    private TreeNode rootCategories;

    @Getter
    @Setter
    private Category selectedCategory;

    @Getter
    @Setter
    private TreeNode selectedNode = new DefaultTreeNode();

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryDao categoryDao;

    @PostConstruct
    public void init() {

        rootCategories = categoryService.buildCategories();
    }

    public void TreeNodeToCategory(NodeSelectEvent nodeSelectEvent) {
        selectedCategory = (Category) nodeSelectEvent.getTreeNode().getData();
    }

    public void onDragDrop(TreeDragDropEvent event) throws IOException {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();
        categoryService.saveSelectedTreeNode(dragNode, dropNode, dropIndex);

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard-categories.xhtml");
    }


}
