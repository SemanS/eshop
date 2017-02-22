package com.webinson.eurofood.utils;

import org.springframework.stereotype.Component;

/**
 * Created by Slavo on 2/20/2017.
 */
@Component
public class doubleToString {

    public String doubleToString(double price) {
        return String.valueOf(price) + " " + "Eur";
    }

}
