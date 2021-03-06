package com.webinson.eurofood.bean;

import com.google.common.collect.Lists;
import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.service.UserService;


/*import freemarker.cache.WebappTemplateLoader;*/
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.validation.constraints.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Slavo on 12/7/2016.
 */
@Component
public class RegisterBean {

    @com.webinson.eurofood.utils.Email(message = "Prosím, zadajte emailovú adresu v správnom tvare!")
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @Size(min = 3, max = 32, message = "Meno musí byť dlhšie ako 3 znaky a kratšie než 32 znakov!")
    private String firstname;

    @Getter
    @Setter
    @Size(min = 3, max = 32, message = "Priezvisko musí byť dlhšie ako 3 znaky a kratšie než 32 znakov!")
    private String lastname;

    @Getter
    @Setter
    @Size(min = 3, max = 20, message = "Heslo musí byť dlhšie ako 3 znaky a kratšie než 20 znakov!")
    private String password;

    @Getter
    @Setter
    @Size(min = 3, max = 128, message = "Adresa musí byť dlhšia ako 3 znaky a kratšia než 128 znakov!")
    private String street;

    @Getter
    @Setter
    @Size(min = 3, max = 32, message = "Telefón musí byť dlhší ako 3 znaky a kratší než 32 znakov!")
    private String phoneNumber;

    @Getter
    @Setter
    @Size(min = 3, max = 32, message = "Zadajte prosím PSČ v správnom tvare!")
    private String postalCode;

    @Getter
    @Setter
    @Size(min = 3, max = 128, message = "Mesto musí byť dlhšie ako 3 znaky a kratšie než 128 znakov!")
    private String city;

    @Getter
    @Setter
    private String company;

    @Getter
    @Setter
    private String ico;

    @Getter
    @Setter
    private String dic;

    @Getter
    @Setter
    @AssertTrue(message = "Musíte súhlasiť s podmienkami ochrany osobných údajov")
    private boolean checkedCondition;

    @Getter
    @Setter
    private String subscribed = "Yes";

    @Autowired
    ServletContext context;

    @Autowired
    public EmailService emailService;

    @Autowired
    private UserService userService;

    /*public String sendTemplateEmail() throws UnsupportedEncodingException, CannotSendEmailException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("ceo@webinson.com", "Správa od eurofood.sk"))
                .to(Lists.newArrayList(new InternetAddress("Slavosmn@gmail.com", "Správa od eurofood.sk")))
                .subject("Správa od eurofood.sk" + "pre")
                .body("Boli ste zaregistrovaný v systéme eurofood.sk. Prajeme príjemné nakupovanie.")
                .encoding("UTF-8").build();

        final Map<String, Object> modelObject = new HashMap<>();
        modelObject.put("tyrannicida", "ahoj");

        emailService.send(email, "order_mail.ftl", modelObject);

        return "confirmation?faces-redirect=true";
    }*/

    public String updateData() throws UnsupportedEncodingException {
        UserDto userDto = new UserDto();
        AddressDto addressDto = new AddressDto();
        Set<AddressDto> addressDtos = new HashSet<AddressDto>();

        addressDto.setFirstName(firstname);
        addressDto.setLastName(lastname);
        addressDto.setStreet(street);
        addressDto.setCity(city);
        addressDto.setPostalCode(postalCode);
        addressDtos.add(addressDto);

        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setIco(ico);
        userDto.setDic(dic);
        userDto.setCompany(company);
        userDto.setPhoneNumber(phoneNumber);
        userDto.setAddressDtos(addressDtos);

        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("ceo@webinson.com", "Správa od eurofood.sk"))
                .to(Lists.newArrayList(new InternetAddress(userDto.getEmail(), "Správa od eurofood.sk")))
                .subject("Správa od eurofood.sk" + "pre" + userDto.getEmail())
                .body("Boli ste zaregistrovaný v systéme eurofood.sk. Prajeme príjemné nakupovanie.")
                .encoding("UTF-8").build();
        emailService.send(email);

        userService.registerNewUserAccount(userDto);
        return "confirmation?faces-redirect=true";
    }


    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean currentRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = false;
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_ADMIN");
            if (ga.getAuthority().equals(sga.getAuthority())) {
                isAdmin = true;
            } else {
                isAdmin = false;
            }
        }
        return isAdmin;
    }
}
