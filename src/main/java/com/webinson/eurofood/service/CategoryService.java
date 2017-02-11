package com.webinson.eurofood.service;

import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.TreeNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Slavo on 12/2/2016.
 */
public interface CategoryService {

    TreeModel createModel();

    void saveNewCategory(Category category);

    CategoryDto getCategoryByUrl(String url);

    List<Category> getNonRootCategories();

    List<String> getStringCategories();

    List<Category> getRootCategories();

    List<String> getStringRootCategories();

    TreeNode buildCategories();

    void saveRootCategory(Category category);

    Category getCategoryByName(String name);

    List<Category> getCategoryRootNodeList();

    void saveSelectedTreeNode(TreeNode dragNode, TreeNode dropNode, int dropIndex);

    int findLastPositionByCategory(Category category);

    int findLastRootPosition();

}
