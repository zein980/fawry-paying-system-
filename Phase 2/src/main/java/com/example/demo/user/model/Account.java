package com.example.demo.user.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Account {
    private boolean isAdmin = false;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double walletBalance;
}
