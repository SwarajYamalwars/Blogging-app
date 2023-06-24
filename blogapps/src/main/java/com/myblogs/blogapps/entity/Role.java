package com.myblogs.blogapps.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Entity
@Table(name = "roles")
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}
