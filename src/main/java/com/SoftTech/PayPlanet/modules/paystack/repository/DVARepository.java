package com.SoftTech.PayPlanet.modules.paystack.repository;

import com.SoftTech.PayPlanet.modules.paystack.model.DedicatedVirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DVARepository extends JpaRepository<DedicatedVirtualAccount, Long> {
}
