package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryAssembler categoryAssembler;

    List<CategoryDto> allCategories = new ArrayList<CategoryDto>();

    /*public TreeNode createRoot() {
        for (Category cat : categoryDao.findAll()) {
            if (cat.getParent() == null) {
                allCategories.add(categoryAssembler.toDto(cat));
            }
        }
        TreeNode rootNode = new DefaultTreeNode(new CategoryDto(), null);

        for (CategoryDto category : allCategories) {
            TreeNode node = new DefaultTreeNode(category, rootNode);
            createNodeCategory(category, node);
        }

        return rootNode;
    }

    public void createNodeCategory(CategoryDto categoryDto, TreeNode node) {

        List<CategoryDto> categoryList = createSubNodes(categoryDto);

        for (CategoryDto cat : categoryList) {
            TreeNode subNode = new DefaultTreeNode(cat, node);
            createNodeCategory(cat, subNode);
        }
        return;
    }*/

    public List<CategoryDto> createSubNodes(CategoryDto categoryDto) {
        List<CategoryDto> categoryNodeList = new ArrayList<CategoryDto>();

        if (categoryDao.findById(categoryDto.getId()) != null) {
            for (Category cat : categoryDao.findByParentId(categoryDto.getId())) {
                categoryNodeList.add(categoryAssembler.toDto(cat));
            }
        }
        return categoryNodeList;
    }

    public TreeModel<CategoryDto> createModel() {
        TreeModel<CategoryDto> treeModel = new ListTreeModel<>();


        for (Category cat : categoryDao.findAll()) {
            if (cat.getParent() == null) {
                allCategories.add(categoryAssembler.toDto(cat));

            }
        }
        buildTreeModel(treeModel, allCategories);


        return treeModel;

    }

    private void buildTreeModel(TreeModel<CategoryDto> treeModel, List<CategoryDto> items) {
        for (CategoryDto item : items) {
            buildTreeModel(treeModel.addChild(item), createSubNodes(item));
        }
    }

    /*@Autowired
    CategoryDao categoryDao;

    public TreeNode createIncomes(String selectedMonth, String selectedCity, String selectedYear) {

        TreeNode rootNode = new DefaultTreeNode(new VykazRadekDto("name", 1.0, 2.0, 3.0), null);

        List<VykazRadekDto> vykazRadekRootNodeList = getIncomeVykazRadekRoot(selectedYear, selectedMonth, selectedCity);

        for (VykazRadekDto vykRad : vykazRadekRootNodeList) {
            TreeNode node = new DefaultTreeNode(vykRad, rootNode);
            createSubIncomes(vykRad, node);
        }
        return rootNode;
    }

    private List<VykazRadekDto> createSubIncomes(VykazRadekDto vykaz, TreeNode node) {
        List<VykazRadekDto> vykazList = createSubCategoryNodes(vykaz);
        try {
            for (VykazRadekDto vyk : vykazList) {
                TreeNode subNode = new DefaultTreeNode(vyk, node);
                createSubIncomes(vyk, subNode);
            }
            return vykazList;
        } finally {
            return vykazList;
        }
    }

    private List<CategoryDto> createSubCategoryNodes(CategoryDto categoryDto) {

        List<CategoryDto> subCategoryNodeList = new ArrayList<>();

        if (categoryDto.getChildren() != null) {
            for (CategoryDto cat : categoryDto.getChildren()) {
                subCategoryNodeList.add(cat);
            }
        }
        return subCategoryNodeList;
    }


    private List<CategoryDto> getCategoryRoot() {

        List<CategoryDto> categories = categoryDao.findAll();
        return null;
    }*/


}
