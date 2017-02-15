package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Repository
public interface ItemDao extends JpaRepository<Item, Long>, QueryDslPredicateExecutor<Item>, PagingAndSortingRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    Item findByUrl(String url);

    Item findById(Long id);

    Page<Item> findByHeader(String header, Pageable pageable);

}
