package com.webinson.eurofood.bean;

import com.webinson.eurofood.assembler.ItemAssembler;
import com.webinson.eurofood.dao.CategoryDao;
import com.webinson.eurofood.dao.ItemDao;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Item;
import com.webinson.eurofood.service.CategoryService;
import com.webinson.eurofood.service.ItemService;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import lombok.Setter;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

/**
 * Created by Slavo on 1/17/2017.
 */
@Component
@ViewScoped
public class ItemDashboardBean {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Getter
    @Setter
    private List<Category> categories;

    @Getter
    @Setter
    private List<Category> rootCategories;

    @Getter
    @Setter
    private List<String> categoriesString;

    private ItemLazyDataModel items;

    @Getter
    @Setter
    private Item selectedItem;

    @Getter
    @Setter
    private String selectedCategory;

    @Getter
    @Setter
    private List<String> rootCategoriesString;

    @Getter
    @Setter
    private UploadedFile file;

    @PostConstruct
    private void init() {
        categories = categoryService.getNonRootCategories();
        categoriesString = categoryService.getStringCategories();
        this.items = new ItemLazyDataModel(itemService);
        selectedItem = new Item();
    }

    public ItemLazyDataModel getItems() {
        return items;
    }

    public void onRowSelect(SelectEvent event) {
        selectedItem = itemDao.findById(((Item) event.getObject()).getId());
        selectedCategory = selectedItem.getCategory().getName();
    }

    public String onSaveItem() throws IOException {
        if (file.getSize() != 0) {

            Resource resource = new ClassPathResource("/images/calculator.png");
            InputStream is = getClass().getResourceAsStream("/eurofood_logo_product.png");
            BufferedImage originalImage = ImageIO.read(file.getInputstream());
            BufferedImage watermarkImage = ImageIO.read(is);
            Watermark filter = new Watermark(Positions.TOP_RIGHT, watermarkImage, 0.5f);
            BufferedImage watermarkedImage = filter.apply(originalImage);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(watermarkedImage, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            selectedItem.setImage(bytes);
        }
        selectedItem.setCategory(categoryDao.findByName(selectedCategory));
        itemDao.save(selectedItem);
        /*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "pretty:dashboard");*/
        FacesContext.getCurrentInstance().getExternalContext().redirect("/dashboard-products.xhtml");
        return "dashboard-products?faces-redirect=true";
    }

    public String onDeleteItem() {
        itemDao.delete(selectedItem);
        return "pretty:dashboard";
    }

    public String getBaseImage(Item item) {
        if (item.getImage() == null) {
            return "";
        }
        String newImage = "";
        newImage = Base64.getEncoder().encodeToString(item.getImage());
        return newImage;
    }

    public void onNewItem() {
        this.selectedItem = new Item();
    }


}
