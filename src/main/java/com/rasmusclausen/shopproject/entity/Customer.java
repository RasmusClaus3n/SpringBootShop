package com.rasmusclausen.shopproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 255, message = "First name cannot be over 255 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 255, message = "Last name cannot be over 255 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address cannot be over 255 characters")
    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne(mappedBy = "customer", optional = false)
    private WebOrder webOrder;
}