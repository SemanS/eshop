package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slavo on 11/23/2016.
 */
@Entity
@Table(name = "category", schema = "eshop")
@Data
@NoArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq")*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> children;

    @Column(name = "img")
    private byte[] image;

    @Column(name = "root")
    private boolean base;

    @Column(name = "url")
    private String url;

    @Column(name = "img_description")
    private byte[] imageDescription;
}
