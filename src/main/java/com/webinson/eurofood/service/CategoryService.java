package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.TreeNode;

/**
 * Created by Slavo on 12/2/2016.
 */
public interface CategoryService {

    /*public TreeNode createRoot();*/

    public TreeModel createModel();

    public void saveNewCategory(Category category);

    public CategoryDto getCategoryByUrl(String url);
}
