package com.webinson.eurofood.entity;

import com.webinson.eurofood.dto.ItemDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Slavo on 1/7/2017.
 */
@Entity
@Table(name = "cartitem", schema = "eshop")
@NoArgsConstructor
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "item_id")
    @Getter
    @Setter
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "shoppingcart_id")
    @Getter
    @Setter
    private ShoppingCart shoppingCart;

    @Column(name = "quantity")
    @Getter
    @Setter
    private int quantity;

}