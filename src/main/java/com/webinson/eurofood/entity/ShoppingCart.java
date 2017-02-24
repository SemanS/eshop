package com.webinson.eurofood.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by Slavo on 1/7/2017.
 */
@Entity
@Table(name = "shoppingcart", schema = "eshop")
@NoArgsConstructor
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToMany(mappedBy = "shoppingCart")
    private Set<CartItem> cartItems;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    @Column(name = "processed")
    private boolean processed;

    @Getter
    @Setter
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timeStamp;

    @Getter
    @Setter
    @Column(name = "order_address")
    private String orderAddress;

}