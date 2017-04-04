package com.webinson.eurofood.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Slavo on 4/4/2017.
 */
@Entity
@Table(name = "staticcontent", schema = "eshop")
@NoArgsConstructor
public class StaticContent {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "o_nas")
    @Getter
    @Setter
    private String oNas;

    @Column(name = "rozvoz")
    @Getter
    @Setter
    private String rozvoz;

    @Column(name = "kontakt")
    @Getter
    @Setter
    private String kontakt;

    @Column(name = "carousel_1")
    @Getter
    @Setter
    private byte[] carousel1;

    @Column(name = "carousel_2")
    @Getter
    @Setter
    private byte[] carousel2;

}
