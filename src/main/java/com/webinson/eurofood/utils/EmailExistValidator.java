package com.webinson.eurofood.utils;

import com.webinson.eurofood.dao.UserDao;
import com.webinson.eurofood.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Slavo on 2/17/2017.
 */
@Component
@RequestScoped
public class EmailExistValidator implements Validator {

    @Autowired
    UserDao userDao;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        String newUsername = (String) o;

        for (User user : userDao.findAll()) {
            if (user.getUsername().equals(newUsername)) {
                /*throw new ValidatorException( fma.getLastMessage() );*/
                /*facesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Your password is NOT strong enough."));*/
                throw new ValidatorException(new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Emailová adresa sa už používa. Zadajte inú.", null));
            }
        }

    }

}
