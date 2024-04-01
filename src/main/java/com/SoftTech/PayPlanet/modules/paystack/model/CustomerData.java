package com.SoftTech.PayPlanet.modules.paystack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "customer_data")
public class CustomerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    Long id;

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String customerCode;
    private String phone;
    private String riskAction;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    DedicatedVirtualAccount dva;
}
