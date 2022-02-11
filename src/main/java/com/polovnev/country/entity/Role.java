package com.polovnev.country.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer role_id;

    @Column(name = "role")
    private String role;

}
