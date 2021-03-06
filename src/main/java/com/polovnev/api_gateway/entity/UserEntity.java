package com.polovnev.api_gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_entity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "activation_code")
    private String activationCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role")
    private Role role;
}
