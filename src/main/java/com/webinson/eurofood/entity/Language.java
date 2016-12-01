package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Slavo on 12/1/2016.
 */
@Entity
@Table(name = "language", schema = "eshop")
@Data
@NoArgsConstructor
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

}
