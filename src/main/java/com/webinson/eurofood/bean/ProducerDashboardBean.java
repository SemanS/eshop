package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.ProducerDao;
import com.webinson.eurofood.dto.ItemDto;
import com.webinson.eurofood.entity.CartItem;
import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Producer;
import com.webinson.eurofood.entity.ShoppingCart;
import com.webinson.eurofood.service.ProducerService;
import com.webinson.eurofood.service.ShoppingCartService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Slavo on 3/25/2017.
 */
@Component
@ViewScoped
public class ProducerDashboardBean {

    @Autowired
    private ProducerService producerService;

    @Autowired
    ProducerDao producerDao;

    @Getter
    private ProducerLazyDataModel producerLazyDataModel;

    @Getter
    @Setter
    private Producer selectedProducer;

    @Getter
    @Setter
    private Producer newProducer = new Producer();

    @PostConstruct
    private void init() {
        this.producerLazyDataModel = new ProducerLazyDataModel(producerService);
    }

    public void onRowSelect(SelectEvent event) {
        selectedProducer = producerDao.findById(((Producer) event.getObject()).getId());
    }

    public String onChangeProducer() throws IOException {
        Producer producer;
        producer = producerDao.findById(selectedProducer.getId());
        producer.setName(selectedProducer.getName());

        producerDao.save(producer);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

    /*Save new producer*/
    public String onSaveProducer() throws IOException {
        Producer producer = new Producer();
        producer.setName(newProducer.getName());
        producerDao.save(producer);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath());
        return "";
    }

}
