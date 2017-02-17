package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.IOException;
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

    /*Root Categories*/
    @Getter
    @Setter
    private List<Category> rootCategories;

    /*Non Root Categories*/
    @Getter
    @Setter
    private List<Category> categories;

    /*@Getter
    @Setter
    private String selectedRootCategory;*/

    /*@Getter
    @Setter
    private String selectedSubCategory;*/

    @Getter
    @Setter
    private Category inputRootCategory;

    @Getter
    @Setter
    private Category inputSubCategory;

    /*Selected Root category*/
    @Getter
    @Setter
    private Category inputSelectedRootCategory;

    /*Selected sub category*/
    @Getter
    @Setter
    private Category inputSelectedSubCategory;

    /*Value to selected one menu*/
    @Getter
    @Setter
    private String selectedRootCategoryAdd;

    /*Main image of root category*/
    @Getter
    @Setter
    private UploadedFile file;

    /*Secondary image of root category*/
    @Getter
    @Setter
    private UploadedFile imgDescription;

    /*Main image of new root category*/
    @Getter
    @Setter
    private UploadedFile newRootFile;

    /*Secondary image of new root category*/
    @Getter
    @Setter
    private UploadedFile newImgDescription;

    /*Main image of new sub category*/
    @Getter
    @Setter
    private UploadedFile newSideImg;

    /*Secondary image of new sub category*/
    @Getter
    @Setter
    private UploadedFile newSideImgDescription;

    @Getter
    @Setter
    private TreeNode selectedNode = new DefaultTreeNode();

    @PostConstruct
    private void init() {
        rootCategories = categoryService.getRootCategories();
        categories = categoryService.getNonRootCategories();
        inputRootCategory = new Category();
        inputSubCategory = new Category();
        initRootCategory();
        initSubCategory();
    }

    public void TreeNodeToCategory(NodeSelectEvent nodeSelectEvent) {
        inputSelectedRootCategory = (Category) nodeSelectEvent.getTreeNode().getData();
    }

    public Category initRootCategory() {
        if (rootCategories.size() == 0) {
            return inputSelectedRootCategory = new Category();
        } else {
            return inputSelectedRootCategory = rootCategories.get(0);
        }
    }

    public Category initSubCategory() {
        if (categories.size() == 0) {
            return inputSelectedSubCategory = new Category();
        } else {
            return inputSelectedSubCategory = categories.get(0);
        }
    }

    /*Change root category*/
    public String onChangeRootCategory() throws IOException {
        Category category;
        category = categoryDao.findById(inputSelectedRootCategory.getId());
        category.setName(inputSelectedRootCategory.getName());
        category.setUrl(inputSelectedRootCategory.getUrl());
        if (file.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(file.getInputstream()));
        }
        if (imgDescription.getSize() != 0) {
            category.setImageDescription(IOUtils.toByteArray(imgDescription.getInputstream()));
        }
        categoryDao.save(category);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

    /*Save new root category*/
    public String onSaveRootCategory() throws IOException {
        Category category = new Category();
        category.setName(inputRootCategory.getName());
        category.setUrl(inputRootCategory.getUrl());
        if (newRootFile.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newRootFile.getInputstream()));
        }
        if (newImgDescription.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newImgDescription.getInputstream()));
        }
        category.setPosition(categoryService.findLastRootPosition());
        categoryService.saveRootCategory(category);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

    /*Add new subCategory*/
    public String onSubCategoryAdd() throws IOException {
        Category category = new Category();
        category.setName(inputSubCategory.getName());
        category.setUrl(inputSubCategory.getUrl());
        if (newSideImg.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newSideImg.getInputstream()));
        }
        if (newSideImgDescription.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newSideImgDescription.getInputstream()));
        }
        category.setParent(categoryDao.findByName(selectedRootCategoryAdd));
        categoryDao.save(category);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

    public String getBaseImage(Category category) {
        if (category.getImage() == null) {
            return "";
        }
        String newImage = "";
        newImage = Base64.getEncoder().encodeToString(category.getImage());
        return newImage;
    }

    public String getBaseImageDescription(Category category) {
        if (category.getImageDescription() == null) {
            return "";
        }
        String newImage = "";
        newImage = Base64.getEncoder().encodeToString(category.getImageDescription());
        return newImage;
    }

}
