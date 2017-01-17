package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Slavo on 10/16/2016.
 */

@Entity
@Table(name = "item", schema = "eshop")
@Data
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Date date;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "int_number")
    private int internalNumber;

    @Column(name = "price_netto")
    private int priceNetto;

    @Column(name = "price_brutto")
    private int priceBrutto;

    @Column(name = "ean")
    private String eanNumber;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Column(name = "pieces_in_carton")
    private int piecesInCarton;

    @Column(name = "carton_in_palette")
    private int cartonInPalette;

    @Column(name = "pieces_in_palette")
    private int piecesInPalette;

    @Column(name = "is_discount")
    private boolean isDiscount;

    @Column(name = "image")
    private byte[] image;

}
