package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Slavo on 1/7/2017.
 */
@Entity
@Table(name = "cartitem", schema = "eshop")
@Data
@NoArgsConstructor
public class CartItem{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "quantity")
    private int quantity;

}