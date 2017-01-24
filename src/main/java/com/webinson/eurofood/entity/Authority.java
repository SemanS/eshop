package com.webinson.eurofood.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Slavo on 1/12/2017.
 */
@Entity
@Table(name = "authorities", schema = "public")
@NoArgsConstructor
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "username")
    @Getter
    @Setter
    private String username;

    @Column(name = "authority")
    @Getter
    @Setter
    private String authority;
}
