package com.webinson.eurofood.bean;

import com.webinson.eurofood.dto.AddressDto;
import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private UserService userService;

    public String updateData() {
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

        userService.registerNewUserAccount(userDto);
        return "confirmation?faces-redirect=true";
    }

    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
