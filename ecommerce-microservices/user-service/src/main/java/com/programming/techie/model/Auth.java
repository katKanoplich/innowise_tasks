package com.programming.techie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "authentications")
@AllArgsConstructor
@RequiredArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public Auth(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
