package com.billing.billing.system.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private  String sku;

    private  String description;
    private  Double price;
    private  Double sellingPrice;
    private   Double mrp;
    private  String brand;
    private  String image;

    @ManyToOne
    private   Category category;

    @ManyToOne
    private  Store store;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
