package com.example.demo.providers.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double servicesPrice;
    @Column(unique = true)
    private String providerName;
    private String servicesName;
    private String RequirementForm;
    private boolean isCash;
}
