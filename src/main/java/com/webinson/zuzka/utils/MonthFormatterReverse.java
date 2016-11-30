package com.webinson.zuzka.utils;

/**
 * Created by Slavo on 17.09.2016.
 */
public class MonthFormatterReverse {

    public String monthFormat(String date) {

        switch (date)

        {
            case "Leden":
                date = "01";
                break;
            case "Únor":
                date = "02";
                break;
            case "Březen":
                date = "03";
                break;
            case "Duben":
                date = "04";
                break;
            case "Květen":
                date = "05";
                break;
            case "Červen":
                date = "06";
                break;
            case "Červenec":
                date = "07";
                break;
            case "Srpen":
                date = "08";
                break;
            case "Září":
                date = "09";
                break;
            case "Říjen":
                date = "10";
                break;
            case "Listopad":
                date = "11";
                break;
            case "Prosinec":
                date = "12";
                break;
        }
        return date;
    }

}
