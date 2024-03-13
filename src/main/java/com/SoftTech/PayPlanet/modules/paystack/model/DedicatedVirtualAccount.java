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


}
