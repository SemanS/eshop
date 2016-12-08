package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
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

    @Autowired
    CategoryService categoryService;

    @PostConstruct
    public void init() {
        rootNode = categoryService.createRoot();
    }


    /*@Getter
    @Setter
    private MenuModel model = new DefaultMenuModel();

    public MenuBean() {
        DefaultSubMenu file = new DefaultSubMenu("File");
        // Create submenu
        DefaultSubMenu help = new DefaultSubMenu("Help");
        // Create menuitem

        DefaultMenuItem open = new DefaultMenuItem("Open");
        // Create menuitem
        DefaultMenuItem edit = new DefaultMenuItem("Edit");
        // Create menuitem
        DefaultMenuItem exit = new DefaultMenuItem("Exit");

        // Create menuitem
        DefaultMenuItem about = new DefaultMenuItem("About Primefaces");
        // Create menuitem
        DefaultMenuItem contact = new DefaultMenuItem("Contact Us");
        // Create menuitem
        DefaultMenuItem helpMenuItem = new DefaultMenuItem("Help");

        // Determine menuitem action
        open.setCommand("#{menuBean.openAction}");

        // Associate menuitem with submenu
        file.addElement(open);
        file.addElement(edit);
        file.addElement(new DefaultSeparator());
        file.addElement(exit);

        help.addElement(about);
        help.addElement(contact);
        help.addElement(new DefaultSeparator());
        help.addElement(helpMenuItem);

        // Associate submenu with menu
        model.addElement(file);
        model.addElement(help);
    }

    public String openAction() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Open action has activiated asynchrounsly !"));
        return "";
    }*/

}