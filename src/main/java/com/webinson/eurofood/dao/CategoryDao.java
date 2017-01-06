package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Long>, QueryDslPredicateExecutor<Category> {
    List<Category> findAll();

    Category findById(Long id);

    List<Category> findByParentId(Long id);

    Category findByUrl(String url);
}
