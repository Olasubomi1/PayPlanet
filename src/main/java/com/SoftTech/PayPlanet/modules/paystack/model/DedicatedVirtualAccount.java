package com.SoftTech.PayPlanet.modules.paystack.model;

import com.SoftTech.PayPlanet.modules.paystack.orm.Assignment;
import com.SoftTech.PayPlanet.modules.paystack.orm.Bank;
import com.SoftTech.PayPlanet.modules.paystack.orm.CustomerData;
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
    Bank bank;
    Assignment assignment;
    CustomerData customer;
}
