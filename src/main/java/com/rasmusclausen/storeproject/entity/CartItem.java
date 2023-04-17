package com.rasmusclausen.storeproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "web_order_id", referencedColumnName = "id", nullable = false)
    private WebOrder webOrder;

}