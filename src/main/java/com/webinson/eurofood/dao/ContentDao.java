package com.webinson.eurofood.dao;

import com.webinson.eurofood.entity.StaticContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Slavo on 4/4/2017.
 */
@Repository
public interface ContentDao extends JpaRepository<StaticContent, Long>, QueryDslPredicateExecutor<StaticContent>, PagingAndSortingRepository<StaticContent, Long>, JpaSpecificationExecutor<StaticContent> {
}
