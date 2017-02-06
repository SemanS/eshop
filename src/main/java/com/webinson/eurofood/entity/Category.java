package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slavo on 11/23/2016.
 */
@Entity
@Table(name = "category", schema = "eshop")
@NoArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq")*/
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Getter
    @Setter
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<Category> children;

    @Column(name = "img_description")
    @Getter
    @Setter
    private byte[] image;

    @Column(name = "root")
    @Getter
    @Setter
    private boolean base;

    @Column(name = "url")
    @Getter
    @Setter
    private String url;

    @Column(name = "img")
    @Getter
    @Setter
    private byte[] imageDescription;
}
