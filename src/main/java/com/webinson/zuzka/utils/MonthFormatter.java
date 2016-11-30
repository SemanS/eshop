package com.webinson.zuzka.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Slavo on 13.09.2016.
 */
public class MonthFormatter {

    public String monthFormat(String date) {

        switch (date)

        {
            case "01":
                date = "Leden";
                break;
            case "02":
                date = "Únor";
                break;
            case "03":
                date = "Březen";
                break;
            case "04":
                date = "Duben";
                break;
            case "05":
                date = "Květen";
                break;
            case "06":
                date = "Červen";
                break;
            case "07":
                date = "Červenec";
                break;
            case "08":
                date = "Srpen";
                break;
            case "09":
                date = "Září";
                break;
            case "10":
                date = "Říjen";
                break;
            case "11":
                date = "Listopad";
                break;
            case "12":
                date = "Prosinec";
                break;
        }
        return date;
    }
}
