package com.example.bankingapplication.Model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId;
    @Column(name = "username", unique=true)
    private String username;
    @Column(name = "password")
    private String password;
}
