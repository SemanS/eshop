package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.TreeNode;

import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
public interface CategoryService {

    /*public TreeNode createRoot();*/

    TreeModel createModel();

    void saveNewCategory(Category category);

    CategoryDto getCategoryByUrl(String url);

    List<Category> getNonRootCategories();
}
