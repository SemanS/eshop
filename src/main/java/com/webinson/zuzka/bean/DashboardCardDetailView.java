package com.webinson.zuzka.bean;

import com.ocpsoft.pretty.PrettyContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by Slavo on 10/18/2016.
 */
@Component
@ViewScoped
public class DashboardCardDetailView {

    @Getter
    @Setter
    @ManagedProperty(value = "#{param.selectedCard}")
    public String selectedCard;

    @PostConstruct
    public void init() {

        String path = PrettyContext.getCurrentInstance().getRequestURL().toURL();
        String segments[] = path.split("/");
        String resultUrl = segments[segments.length - 1];
        selectedCard = resultUrl;

        //selectedCard = PrettyContext.getCurrentInstance().getRequestURL().toURL().substring(11);

    }

}
