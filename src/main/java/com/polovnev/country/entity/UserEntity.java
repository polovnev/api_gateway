package com.polovnev.country.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_entity")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "username")
    private String username;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role", nullable=false)
    private Role role;
}
