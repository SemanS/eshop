package com.webinson.eurofood.bean;

import com.webinson.eurofood.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FlowEvent;
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


    private boolean skip;


    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + userDto.getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case userDto goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}
