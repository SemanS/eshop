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
    private TreeNode selectedNode = new DefaultTreeNode();

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
    private UploadedFile file;

    @Getter
    @Setter
    private UploadedFile imgDescription;

    @Getter
    @Setter
    private UploadedFile newRootFile;

    @Getter
    @Setter
    private UploadedFile newSubFile;

    @Getter
    @Setter
    private UploadedFile newImg;

    @Getter
    @Setter
    private UploadedFile newSideImg;

    @Getter
    @Setter
    private UploadedFile newSideImgDescription;

    @Getter
    @Setter
    private UploadedFile newImgDescription;

    @Getter
    @Setter
    private Part fileChangeSub;

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

    /*public String onChangeSubCategory() throws IOException {
        Category category;
        category = categoryDao.findByName(selectedSubCategory);
        category.setName(inputSelectedSubCategory.getName());
        category.setUrl(inputSelectedSubCategory.getUrl());
        category.setImage(IOUtils.toByteArray(fileChangeSub.getInputStream()));
        categoryDao.save(category);
        return "pretty:dashboard";
    }*/

    /*Save new root category*/
    public String onSaveRootCategory() throws IOException {
        Category category = new Category();
        category.setName(inputRootCategory.getName());
        category.setUrl(inputRootCategory.getUrl());
        if (newRootFile.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newImg.getInputstream()));
        }
        /*if (imgDescription.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newImgDescription.getInputstream()));
        }*/
        category.setPosition(categoryService.findLastRootPosition());
        categoryService.saveRootCategory(category);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

    public String onSaveSubCategory() throws IOException {
        Category category = new Category();
        category.setName(inputSubCategory.getName());
        category.setUrl(inputSubCategory.getUrl());
        category.setParent(categoryService.getCategoryByName(selectedRootCategory));
        if (newSubFile.getSize() != 0) {
            category.setImage(IOUtils.toByteArray(newSubFile.getInputstream()));
        }
        categoryService.saveRootCategory(category);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "pretty:dashboard";
    }

    public String onSubCategoryAssign() {
        Category category = categoryDao.findByName(selectedSubCategory);
        category.setParent(categoryDao.findByName(selectedRootCategoryAssign));
        categoryDao.save(category);
        return "pretty:dashboard";
    }

    /*public void onUploadImage() throws IOException {
        if (file != null) {
            Category category = categoryDao.findById(inputSelectedRootCategory.getId());
            category.setImage(IOUtils.toByteArray(file.getInputstream()));
            categoryDao.save(category);
            *//*FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);*//*
        }
    }*/

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
        return "pretty:dashboard";
    }

    /*public String onSubCategoryAdd() throws IOException {
        Category category = new Category();
        category.setName(inputSubCategory.getName());
        category.setUrl(inputSubCategory.getUrl());
        category.setImage(IOUtils.toByteArray(fileNewSide.getInputStream()));
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
    }*/

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

    /*public void handleFileUpload(AjaxBehaviorEvent event) throws IOException {
        System.out.println("file size: " + file.getSize());
        System.out.println("file type: " + file.getContentType());
        System.out.println("file info: " + file.getHeader("Content-Disposition"));
        inputSelectedRootCategory.setImage(IOUtils.toByteArray(file.getInputStream()));
        categoryDao.save(inputSelectedRootCategory);
    }*/

}
