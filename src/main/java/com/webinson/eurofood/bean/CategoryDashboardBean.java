package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
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
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

/**
 * Created by Slavo on 1/17/2017.
 */
@Component
@ViewScoped
public class CategoryDashboardBean implements Serializable {

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

    /*Third image*/
    @Getter
    @Setter
    private UploadedFile imgCategoryAsSubcategoryImage;

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
    private UploadedFile categoryAsSubcategoryImage;

    @Getter
    @Setter
    private UploadedFile newCategoryAsSubcategoryImage;

    @Getter
    @Setter
    private UploadedFile newSideCategoryAsSubcategoryImage;

    @Getter
    @Setter
    private TreeNode selectedNode = new DefaultTreeNode();

    @Getter
    @Setter
    private Category selectedCategory;

    public void TreeNodeToCategory(NodeSelectEvent nodeSelectEvent) {
        selectedCategory = (Category) nodeSelectEvent.getTreeNode().getData();
    }

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
        if (rootCategories.size() == 0) {
            return selectedCategory = new Category();
        } else {
            return selectedCategory = rootCategories.get(0);
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
        category = categoryDao.findById(selectedCategory.getId());
        category.setName(selectedCategory.getName());
        category.setUrl(selectedCategory.getUrl());
        if (file.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(file.getInputstream()));
        }
        if (imgDescription.getSize() != 0) {
            category.setImageDescription(IOUtils.toByteArray(imgDescription.getInputstream()));
        }
        if (imgCategoryAsSubcategoryImage.getSize() != 0) {
            category.setImageAsSubcategory(IOUtils.toByteArray(imgCategoryAsSubcategoryImage.getInputstream()));
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
        category.setBase(true);
        if (newRootFile.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newRootFile.getInputstream()));
        }
        if (newImgDescription.getSize() != 0) {
            category.setImageDescription(IOUtils.toByteArray(newImgDescription.getInputstream()));
        }
        if (newCategoryAsSubcategoryImage.getSize() != 0) {
            category.setImageAsSubcategory(IOUtils.toByteArray(newCategoryAsSubcategoryImage.getInputstream()));
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
        category.setBase(false);
        if (newSideImg.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newSideImg.getInputstream()));
        }
        if (newSideImgDescription.getSize() != 0) {
            category.setImageDescription(IOUtils.toByteArray(newSideImgDescription.getInputstream()));
        }
        if (newSideCategoryAsSubcategoryImage.getSize() != 0) {
            category.setImageAsSubcategory(IOUtils.toByteArray(newSideCategoryAsSubcategoryImage.getInputstream()));
        }
        category.setParent(categoryDao.findByName(selectedRootCategoryAdd));
        category.setPosition(categoryService.findLastPositionByCategoryForBean(categoryDao.findByName(selectedRootCategoryAdd)));
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

    public String getImageAsSubcategory(Category category) {
        if (category.getImageAsSubcategory() == null) {
            return "";
        }
        String newImage = "";
        newImage = Base64.getEncoder().encodeToString(category.getImageAsSubcategory());
        return newImage;
    }

    public void onDeleteCategory() throws IOException {
        if (selectedCategory != null) {
            categoryService.deleteCategory(selectedCategory);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard-categories.xhtml");
    }

}
