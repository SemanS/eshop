package com.webinson.eurofood.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.QCategory;
import com.webinson.eurofood.entity.QItem;
import com.webinson.eurofood.service.CategoryService;
import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager entityManager;

    List<CategoryDto> allCategories = new ArrayList<CategoryDto>();

    @Override
    public void saveNewCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public CategoryDto getCategoryByUrl(String url) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto = categoryAssembler.toDto(categoryDao.findByUrl(url));
        return categoryDto;
    }

    @Override
    public List<Category> getNonRootCategories() {
        /*Category category = new Category();*/

        final JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        return query.from(category).where(category.parent.isNotNull()).fetch();
    }

    @Override
    public List<Category> getRootCategories() {
        final JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        return query.from(category).where(category.parent.isNull()).fetch();
    }

    @Override
    public List<String> getStringRootCategories() {

        final JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        List<Category> categories = query.from(category).where(category.parent.isNull()).fetch();
        List<String> stringCategories = new ArrayList<String>();
        for (Category cat : categories) {
            stringCategories.add(cat.getName());
        }
        return stringCategories;

    }

    @Override
    public List<String> getStringCategories() {
        final JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        List<Category> categories = query.from(category).where(category.parent.isNotNull()).fetch();
        List<String> stringCategories = new ArrayList<String>();
        for (Category cat : categories) {
            stringCategories.add(cat.getName());
        }
        return stringCategories;
    }

    public void saveItemByUrl(String url, String text) {

    }

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

}
