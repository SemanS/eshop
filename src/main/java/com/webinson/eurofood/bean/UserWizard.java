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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Slavo on 12/8/2016.
 */
@Component
@ViewScoped
public class UserWizard implements Serializable {

    @Getter
    @Setter
    @Size(min = 3, max = 32, message = "Meno musí byť dlhšie ako 3 znaky a kratšie než 32 znakov!")
    private String facturationFirstname;

    @Getter
    @Setter
    @Size(min = 3, max = 32, message = "Priezvisko musí byť dlhšie ako 3 znaky a kratšie než 32 znakov!")
    private String facturationLastname;

    @Getter
    @Setter
    private String facturationCompany;

    @Getter
    @Setter
    private String facturationIco;

    @Getter
    @Setter
    private String facturationDic;

    @Getter
    @Setter
    @Size(min = 3, max = 128, message = "Adresa musí byť dlhšia ako 3 znaky a kratšia než 128 znakov!")
    private String facturationStreet;

    @Getter
    @Setter
    @Size(min = 3, max = 32, message = "Zadajte prosím PSČ v správnom tvare!")
    private String facturationPostalCode;

    @Getter
    @Setter
    @Size(min = 3, max = 128, message = "Mesto musí byť dlhšie ako 3 znaky a kratšie než 128 znakov!")
    private String facturationCity;

    @Getter
    @Setter
    private UserDto userDto = new UserDto();

    @Getter
    @Setter
    AddressDto selectedAddressDto;

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

    @Getter
    @Setter
    private int currentLevel = 1;

    @PostConstruct
    public void init() {
        radioValueFacturation = "Yes";
        radioValueDelivery = "Yes";
    }

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
        selectedAddressDto = userService.getAddressByUsername(authentication.getName());
        return selectedAddressDto.getFirstName() + " " + selectedAddressDto.getLastName() + ", " + selectedAddressDto.getStreet() + ", " + selectedAddressDto.getCity() + ", " + selectedAddressDto.getPostalCode();
    }

    public String onContinue() {
        if (userContinue.equals("Yes")) {
            return "register?faces-redirect=true";
        } else {
            return "anonymousCheckout?faces-redirect=true";
        }
    }

    public String saveAll() {
        if (radioValueFacturation == "No") {

        }
        return "";
    }

    public String onFlowProcess(FlowEvent event) {

        if (facturationFirstname != null) {
            System.out.println("daco");

        }
        return event.getNewStep();

    }
}
