package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.ContentDao;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.StaticContent;
import com.webinson.eurofood.service.StaticContentService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by Slavo on 4/4/2017.
 */
@ViewScoped
@Component
public class ContentDashboardBean {


    @Autowired
    StaticContentService contentService;

    @Autowired
    ContentDao contentDao;

    @Getter
    @Setter
    private String oNas;

    @Getter
    @Setter
    private String rozvoz;

    @Getter
    @Setter
    private String kontakt;

    @Getter
    @Setter
    private String carousel1Content;

    @Getter
    @Setter
    private String carousel2Content;

    @Getter
    @Setter
    private UploadedFile carousel1;

    @Getter
    @Setter
    private UploadedFile carousel2;

    @PostConstruct
    public void init() {

        carousel1Content = contentService.getCarousel1();
        carousel2Content = contentService.getCarousel2();
        oNas = contentService.getONasContent();
        rozvoz = contentService.getRozvozContent();
        kontakt = contentService.getKontaktContent();

    }

    public String onSaveONas() throws IOException {

        StaticContent sc = contentDao.getOne(1L);
        sc.setONas(oNas);
        contentDao.save(sc);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

    public String onSaveRozvoz() throws IOException {

        StaticContent sc = contentDao.getOne(1L);
        sc.setRozvoz(rozvoz);
        contentDao.save(sc);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

    public String onSaveKontakt() throws IOException {

        StaticContent sc = contentDao.getOne(1L);
        sc.setKontakt(kontakt);
        contentDao.save(sc);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

}
