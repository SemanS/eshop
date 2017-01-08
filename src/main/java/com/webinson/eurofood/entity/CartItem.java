package com.webinson.eurofood.entity;

import com.webinson.eurofood.dto.ItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Slavo on 1/7/2017.
 */
@Entity
@Table(name = "cartitem", schema = "eshop")
@Data
@NoArgsConstructor
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
    @JoinColumn(name = "item_id")*/
    @Column(name = "item_id")
    /*@ManyToOne*/
    /*@JoinColumn(name = "item_id")*/
    private Item item;

    @ManyToOne
    @JoinColumn(name = "shoppingcart_id")
    private ShoppingCart shoppingCart;

    @Column(name = "quantity")
    private int quantity;

}