package com.webinson.eurofood.service.impl;

import com.webinson.eurofood.dao.ContentDao;
import com.webinson.eurofood.dao.UserDao;
import com.webinson.eurofood.service.StaticContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Slavo on 4/4/2017.
 */
@Service
public class StaticContentServiceImpl implements StaticContentService {

    @Autowired
    ContentDao contentDao;


    @Override
    public String getONasContent() {
        return contentDao.getOne(1L).getONas();
    }

    @Override
    public String getRozvozContent() {
        return contentDao.getOne(1L).getRozvoz();
    }

    @Override
    public String getKontaktContent() {
        return contentDao.getOne(1L).getKontakt();
    }
}
