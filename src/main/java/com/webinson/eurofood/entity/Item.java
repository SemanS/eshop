package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Created by Slavo on 10/16/2016.
 */

@Entity
@Table(name = "item", schema = "eshop")
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header")
    @Getter
    @Setter
    private String header;

    @Column(name = "text")
    @Getter
    @Setter
    private String text;

    @Column(name = "date")
    @Getter
    @Setter
    private Date date;

    @Column(name = "url")
    @Getter
    @Setter
    private String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    @Getter
    @Setter
    private Category category;

    @Column(name = "int_number")
    @Getter
    @Setter
    private int internalNumber;

    @Column(name = "price_netto", columnDefinition="Decimal(10,2) default '0.00'")
    @Getter
    @Setter
    private double priceNetto;

    @Column(name = "price_netto_discount", columnDefinition="Decimal(10,2) default '0.00'")
    @Getter
    @Setter
    private double priceNettoDiscount;

    @Column(name = "price_brutto_discount", columnDefinition="Decimal(10,2) default '0.00'")
    @Getter
    @Setter
    private double priceBruttoDiscount;

    @Column(name = "price_brutto", columnDefinition="Decimal(10,2) default '0.00'")
    @Getter
    @Setter
    private double priceBrutto;

    @Column(name = "ean")
    @Getter
    @Setter
    private String eanNumber;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    @Getter
    @Setter
    private Producer producer;

    @Column(name = "pieces_in_carton")
    @Getter
    @Setter
    private int piecesInCarton;

    @Column(name = "carton_in_palette")
    @Getter
    @Setter
    private int cartonInPalette;

    @Column(name = "pieces_in_palette")
    @Getter
    @Setter
    private int piecesInPalette;

    @Column(name = "is_discount")
    @Getter
    @Setter
    private boolean isDiscount;

    @Column(name = "is_kg")
    @Getter
    @Setter
    private boolean isKg;

    @Column(name = "image")
    @Getter
    @Setter
    private byte[] image;

    @Column(name = "number_of_product")
    @Getter
    @Setter
    private int numberOfProduct;

}
