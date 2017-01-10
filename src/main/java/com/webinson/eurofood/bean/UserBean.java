package com.webinson.eurofood.bean;

import com.webinson.eurofood.dto.UserDto;
import com.webinson.eurofood.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Created by Slavo on 12/7/2016.
 */
@Component
public class UserBean {

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
    private int ico;

    @Getter
    @Setter
    private String dic;

    @Getter
    @Setter
    @AssertTrue(message = "Musíte súhlasiť s podmienkami ochrany osobných údajov")
    private boolean checkedCondition;

    @Autowired
    private UserService userService;

    public String updateData() {
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userService.registerNewUserAccount(userDto);
        return "confirmation?faces-redirect=true";
    }
}
