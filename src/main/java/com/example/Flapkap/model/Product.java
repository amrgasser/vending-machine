package com.example.Flapkap.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "amount_available")
    private Integer amountAvailable;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User seller;
}
