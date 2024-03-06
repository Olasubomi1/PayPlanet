package com.SoftTech.PayPlanet.modules.paystack.repository;

import com.SoftTech.PayPlanet.modules.paystack.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
