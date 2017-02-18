package com.webinson.eurofood.bean;

import com.webinson.eurofood.assembler.CategoryAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;

import java.util.List;

/**
 * Created by Slavo on 12/1/2016.
 */
@Component
@SessionScope
public class SideMenuBean {

    @Getter
    private List<CategoryDto> categories;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryAssembler categoryAssembler;

    @Autowired
    CategoryDao categoryDao;

    @PostConstruct
    public void init() {
        categories = categoryAssembler.toDtos(categoryDao.findAll());
    }

    public void onTabChange(TabChangeEvent event) {
        String activeIndex = ((AccordionPanel) event.getComponent()).getActiveIndex();

        System.out.println("Active:" + activeIndex);
    }

}