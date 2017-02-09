package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Slavo on 12/2/2016.
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Long>, QueryDslPredicateExecutor<Category>, PagingAndSortingRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    List<Category> findAll();

    Category findById(Long id);

    List<Category> findByParentId(Long id);

    Category findByUrl(String url);

    Category findByName(String name);

}
