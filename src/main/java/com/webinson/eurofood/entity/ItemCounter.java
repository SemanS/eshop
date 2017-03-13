package com.webinson.eurofood.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Slavo on 3/9/2017.
 */
@Entity
@Table(name = "itemcounter", schema = "eshop")
@NoArgsConstructor
public class ItemCounter implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Getter
    @Setter
    private Item item;

    @Column(name = "counter")
    @Getter
    @Setter
    private int counter;

}
