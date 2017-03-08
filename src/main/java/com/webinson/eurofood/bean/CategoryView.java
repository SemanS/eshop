package com.webinson.eurofood.bean;

import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.Parameter;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slavo on 2/24/2017.
 */
@Component
@ELBeanName(value = "categoryView")
@SessionScope
@Join(path = "/category/{categoryUrl}", to = "/eshop.xhtml")
public class CategoryView implements Serializable {

    @Autowired
    ItemService itemService;

    @Parameter
    @Deferred
    @Getter
    @Setter
    private String categoryUrl;

    @Getter
    @Setter
    private CategoryDto selectedCategory;

    @Getter
    @Setter
    private String selectedCategoryImage;

    @Autowired
    CategoryAssembler categoryAssembler;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @RequestAction
    @Deferred
    @IgnorePostback
    public String loadCategory() throws IOException {

        if (categoryUrl != null) {
            this.selectedCategory = categoryService.getCategoryByUrl(categoryUrl);
            return selectedCategory.getUrl();
        }

        // Add a message here, "The item {..} could not be found."
        return "";
    }

    public List<ItemDto> allItemsByCategory() {
        return itemService.getItemsByCategory(this.selectedCategory);
    }

    public List<CategoryDto> allCategoriesByCategory() {
        return categoryService.getCategoriesByCategory(this.selectedCategory);
    }

    public void onCategory(Category category) throws IOException {
        selectedCategory = categoryAssembler.toDto(categoryDao.findById(category.getId()));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/category/" + category.getUrl());
    }

    public void onCategoryTree(CategoryDto categoryDto) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/category/" + categoryDto.getUrl());
    }

    public String onImageDescriptionChange() {
        selectedCategoryImage = categoryAssembler.toDto(categoryDao.findById(this.selectedCategory.getId())).getImageDescription();
        return selectedCategoryImage;
    }

}
