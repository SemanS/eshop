package com.webinson.eurofood.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.QCategory;
import com.webinson.eurofood.service.CategoryService;
import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

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
        List<Category> categories = categoryDao.findAll();
        for (Category cat : categories) {
            if (cat.getParent() == null) {
                allCategories.add(categoryAssembler.toDto(cat));
            }
        }
        buildTreeModel(treeModel, allCategories);
        return treeModel;
    }

    private void buildTreeModel(TreeModel<CategoryDto> treeModel, List<CategoryDto> items) {
        for (CategoryDto item : items) {
            /*buildTreeModel(treeModel.addChild(item), createSubNodes(item));*/
            treeModel.addChild(item);
        }
    }

    @Override
    public TreeNode buildCategories() {
        TreeNode rootNode = new DefaultTreeNode(new Category(), null);
        List<Category> categoryRootNodeList = getCategoryRootNodeList();

        for (Category cat : categoryRootNodeList) {
            TreeNode node = new DefaultTreeNode(cat, rootNode);
            createSubCategories(cat, node);
        }

        return rootNode;
    }

    public List<Category> getCategoryRootNodeList() {
        List<Category> rootCategories = new ArrayList<Category>();
        for (Category cat : findAllSorted()) {
            if (cat.getParent() == null) {
                rootCategories.add(cat);
            }
        }
        return rootCategories;
    }

    private List<Category> createSubCategories(Category category, TreeNode node) {
        List<Category> categoriesList = getSubCategories(category);
        try {
            for (Category cat : categoriesList) {
                TreeNode subNode = new DefaultTreeNode(cat, node);
                /*createSubCategories(cat, subNode);*/
            }
            return categoriesList;
        } finally {
            return categoriesList;
        }
    }

    private List<Category> getSubCategories(Category category) {
        List<Category> subCategoriesNodeList = new ArrayList<>();
        if (category.getChildren() != null) {
            for (Category cat : findAllSubSorted(category)) {
                subCategoriesNodeList.add(cat);
            }
        }
        return subCategoriesNodeList;
    }

    @Override
    public void saveRootCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        final JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        return query.from(category).where(category.name.eq(name)).fetchOne();
    }

    @Override
    public void saveSelectedTreeNode(TreeNode dragNode, TreeNode dropNode, int dropIndex) {
        List<Category> rootCategories = new ArrayList<>();

        Category draggedCategory = (Category) dragNode.getData();
        int actualPosition = draggedCategory.getPosition();
        draggedCategory.setPosition(dropIndex);
        Category droppedCategory = (Category) dropNode.getData();

        if (droppedCategory.getName() != null) {
            for (Category cat : droppedCategory.getChildren()) {
                if ((cat.getPosition() <= actualPosition) && cat.getId() != draggedCategory.getId()) {
                    cat.setPosition(cat.getPosition() + 1);
                    categoryDao.save(cat);
                }
            }
            boolean isTrue = false;
            if(droppedCategory.getChildren().size() == 0) {
                droppedCategory.getChildren().add(draggedCategory);
                draggedCategory.setParent(droppedCategory);
            }
            for (Category cat : droppedCategory.getChildren()) {
                if (cat.getParent().getId() != draggedCategory.getParent().getId()) {
                    isTrue = true;
                }
            }
            if (isTrue) {
                droppedCategory.getChildren().add(draggedCategory);
                draggedCategory.setParent(droppedCategory);
                /*droppedCategory.getChildren().add(draggedCategory);
                draggedCategory.setParent(droppedCategory);*/
                /*draggedCategory.setParent(droppedCategory);*/
            }
            /*draggedCategory.setPosition(findLastPositionByCategory(droppedCategory));*/
            categoryDao.save(droppedCategory);
            categoryDao.save(draggedCategory);
        }

        if (droppedCategory.getName() == null) {

            for (Category cat : getRootCategories()) {
                if (cat.getPosition() >= draggedCategory.getPosition()) {
                    cat.setPosition(cat.getPosition() + 1);
                    categoryDao.save(cat);
                }
            }

            draggedCategory.setParent(null);
            /*draggedCategory.setPosition(findLastPositionByCategory(droppedCategory));*/
            categoryDao.save(draggedCategory);
        }

    }

    @Override
    public int findLastPositionByCategory(Category category) {

        int lastPosition = 0;
        if (category.getParent() == null) {
            for (Category cat : getRootCategories()) {
                if (cat.getPosition() > lastPosition) {
                    lastPosition = cat.getPosition();
                }
            }
        }

        if (category.getParent() != null) {
            for (Category cat : categoryDao.findByParentId((Long) category.getParent().getId())) {
                if (cat.getPosition() > lastPosition) {
                    lastPosition = cat.getPosition();
                }
            }
        }
        return lastPosition;
    }

    public List<Category> findAllSorted() {
        final JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        return query.from(category).orderBy(category.position.asc()).fetch();
    }

    public List<Category> findAllSubSorted(Category cat) {
        final JPAQuery<Category> query = new JPAQuery<>(entityManager);
        QCategory category = QCategory.category;
        return query.from(category).orderBy(category.position.asc()).where(category.parent.id.eq(cat.getId())).fetch();
    }


}
