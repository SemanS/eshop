package com.webinson.eurofood.bean;

import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by Slavo on 3/10/2017.
 */
@Component
@ViewScoped
public class CategoryDashboardTreeTableBean {

    @Getter
    @Setter
    private TreeNode rootCategories;

    @Autowired
    CategoryService categoryService;

    @PostConstruct
    public void init() {
        rootCategories = categoryService.buildCategories();
    }

    public void onDragDrop(TreeDragDropEvent event) throws IOException {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();

        Category draggedCategory = (Category) dragNode.getData();
        Category droppedCategory = (Category) dropNode.getData();

            categoryService.saveSelectedTreeNode(dragNode, dropNode, dropIndex);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Your message: "));

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard-categories.xhtml");
    }

}
