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
@Table(name = "assignment")
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    Long id;
    private int integration;
    private int assigneeId;
    private String assigneeType;
    private boolean expired;
    private String accountType;
    private String assignedAt;

    @OneToOne(mappedBy = "assignment")
    @JsonIgnore
    DedicatedVirtualAccount dva;
}
