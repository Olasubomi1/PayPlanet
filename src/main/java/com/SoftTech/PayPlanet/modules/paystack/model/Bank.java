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
@Table(name = "bank")
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    Long id;
    private int bankId;
    private String name;
    private String slug;

    @OneToOne(mappedBy = "bank")
    @JsonIgnore
    DedicatedVirtualAccount dva;
}
