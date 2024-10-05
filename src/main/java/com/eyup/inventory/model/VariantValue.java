package com.eyup.inventory.model;

import jakarta.persistence.*;

@Entity
public class VariantValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valueName;

    // Getters and Setters
}

