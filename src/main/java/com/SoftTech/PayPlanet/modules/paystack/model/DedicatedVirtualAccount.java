package com.SoftTech.PayPlanet.modules.paystack.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "paystack_virtual_account")
public class DedicatedVirtualAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private int DVAId;
    private String accountName;
    private String accountNumber;
    private boolean assigned;
    private String currency;
    private String metadata;
    private boolean active;
    private String createdAt;
    private String updatedAt;

    @OneToOne
    @JoinTable(
            name = "dva_bank",
            joinColumns = @JoinColumn(name = "dva_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "bank_id", referencedColumnName = "id")
    )
    Bank bank;

    @OneToOne
    @JoinTable(
            name = "dva_assignment",
            joinColumns = @JoinColumn(name = "dva_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "assignment_id", referencedColumnName = "id")
    )
    Assignment assignment;

    @OneToOne
    @JoinTable(
            name = "dva_customer",
            joinColumns = @JoinColumn(name = "dva_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "customer_id", referencedColumnName = "id")
    )
    CustomerData customer;
}
