package com.webinson.eurofood.bean;

import com.webinson.eurofood.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FlowEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    private boolean skip;

    @Getter
    @Setter
    private String userContinue;

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + userDto.getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public String onContinue() {
        if (userContinue.equals("Yes")) {
            return "register?faces-redirect=true";
        } else {
            return "confirmation?faces-redirect=true";
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
