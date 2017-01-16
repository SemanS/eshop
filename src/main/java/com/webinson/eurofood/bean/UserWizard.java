package com.webinson.eurofood.bean;

import com.webinson.eurofood.dao.UserDao;
import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.entity.Address;
import com.webinson.eurofood.entity.User;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Slavo on 12/8/2016.
 */
@Component
@ViewScoped
public class UserWizard implements Serializable {

    @Getter
    @Setter
    private UserDto userDto = new UserDto();

    @Getter
    @Setter
    private Address address;

    @Getter
    @Setter
    private boolean skip;

    @Getter
    @Setter
    private String userContinue = "Yes";

    @Getter
    @Setter
    private String radioValueFacturation = "Yes";

    @Getter
    @Setter
    private String radioValueDelivery = "Yes";

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterBean registerBean;

    @PostConstruct
    public void init() {
        radioValueFacturation = "Yes";
        radioValueDelivery = "Yes";
    }

    /*public void selectRadioItem() {
        String selectedItem = this.radioValue;
        System.out.println(selectedItem);
    }*/

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + userDto.getEmail());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public String currentUserAddress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AddressDto addressDto = new AddressDto();
        addressDto = userService.getAddressByUsername(authentication.getName());
        return addressDto.getFirstName() + " " + addressDto.getLastName() + ", " + addressDto.getStreet() + ", " + addressDto.getCity() + ", " + addressDto.getPostalCode();
    }

    public String onContinue() {
        if (userContinue.equals("Yes")) {
            return "register?faces-redirect=true";
        } else {
            return "anonymousCheckout?faces-redirect=true";
        }
    }

    public String onFlowProcess(FlowEvent event) {

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() != "anonymousUser") {
            return event.getNewStep();
        }*/
        /*if (skip) {
            skip = false;   //reset in case userDto goes back
            return "confirm";
        } */
        /*else {
            return event.getNewStep();
        }*/
        return event.getNewStep();
    }
}
