package com.webinson.eurofood.dto;

import com.webinson.eurofood.entity.Category;
import com.webinson.eurofood.entity.Language;
import com.webinson.eurofood.entity.Producer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Slavo on 10/17/2016.
 */
@Data
public class ItemDto {

    private Long id;
    private String header;
    private String text;
    private Date date;
    private String url;
    private Category category;
    private int internalNumber;
    private int priceNetto;
    private int priceBrutto;
    private String eanNumber;
    private Producer producer;
    private int piecesInCarton;
    private int cartonInPalette;
    private int piecesInPalette;

}
