package com.rasmusclausen.shopproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "web_order")
public class WebOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "webOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "total_sum", nullable = false)
    private Double totalSum;

    @CreationTimestamp
    private Date created;

    public void addCartItem(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
        cartItem.setWebOrder(this);
    }

}