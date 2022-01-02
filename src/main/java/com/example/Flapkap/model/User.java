package com.example.Flapkap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Value;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name="username",unique = true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="depost")
    private Long deposit;

    @Column(name="role")
    private String role;

}
