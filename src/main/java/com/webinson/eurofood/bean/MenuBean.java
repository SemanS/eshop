package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dto.CategoryDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.service.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.model.tree.TreeModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Slavo on 12/1/2016.
 */
@Component
@ApplicationScope
public class MenuBean {

    @Getter
    @Setter
    private TreeNode rootNode;

    @Getter
    @Setter
    private TreeModel<CategoryDto> treeModel;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryDao categoryDao;

    /*@Setter
    private DefaultStreamedContent image;*/

    @PostConstruct
    public void init() {

        treeModel = categoryService.createModel();
        //rootNode = categoryService.createRoot();
    }


    public DefaultStreamedContent getImage() throws IOException {

        /*File imageFile = new File("");

        InputStream stream1 = this.getClass().getClassLoader().getResourceAsStream("/images/mliecne_bg.png");  //or something else
        image = new DefaultStreamedContent(stream1, "image/png");

        return image;
        FacesContext context = FacesContext.getCurrentInstance();*/

        /*if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {*/
        // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        //String studentId = context.getExternalContext().getRequestParameterMap().get("studentId");
        Category category = categoryDao.findById(Long.valueOf(4));
        return new DefaultStreamedContent(new ByteArrayInputStream(category.getImage()));
        /*}*/
    }

}