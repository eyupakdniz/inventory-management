package com.eyup.inventory.model;

import jakarta.persistence.*;
@Entity
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String variantName;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private ProductTemplate productTemplate;

    // Getters and Setters
}