package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Slavo on 1/13/2017.
 */
@Entity
@Table(name = "company", schema = "public")
@Data
@NoArgsConstructor
public class Company implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String street;

    @Column(name = "ico")
    private int postalCode;

    @Column(name = "dic")
    private int city;

    @Column(name = "ic_dph")
    private int icDph;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
