package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Slavo on 1/7/2017.
 */
@Entity
@Table(name = "shoppingcart", schema = "eshop")
@Data
@NoArgsConstructor
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@OneToOne
    public Buyer buyer;*/

    /*@OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.PERSIST)*/
    /*@JoinColumn(name = "cartitem_id")*/
    /*@OneToMany(cascade = {CascadeType.ALL})*/
    /*@JoinColumn(name = "cartitem_id")*/
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.PERSIST)
    private Set<CartItem> cartItems;

}