package com.webinson.zuzka.bean;

import com.google.common.collect.Lists;
import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.impl.EmailImpl;
import it.ozimov.springboot.templating.mail.service.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ViewScoped;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Slavo on 11/11/2016.
 */
@Component
@ViewScoped
public class GetInTouchView {

    @Autowired
    public EmailService emailService;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String textarea;

    @com.webinson.zuzka.utils.Email(message = "must be a valid mail")
    @Getter
    @Setter
    private String email;

    /*public String updateData() {
        System.out.println(name);
        return "confirmation?faces-redirect=true";
    }*/

    public String updateData() throws UnsupportedEncodingException {
        final Email email = EmailImpl.builder()
                .from(new InternetAddress("ceo@webinson.com", "Message from webinson"))
                .to(Lists.newArrayList(new InternetAddress(getEmail(), "asd")))
                .subject(name)
                .body(textarea)
                .encoding(Charset.forName("UTF-8")).build();

        emailService.send(email);
        return "confirmation?faces-redirect=true";
    }

}
