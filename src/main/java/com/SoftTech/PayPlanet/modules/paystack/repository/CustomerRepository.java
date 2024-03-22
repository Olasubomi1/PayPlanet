package com.SoftTech.PayPlanet.modules.paystack.repository;

import com.SoftTech.PayPlanet.modules.paystack.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmailAddress(String emailAddress);
}
