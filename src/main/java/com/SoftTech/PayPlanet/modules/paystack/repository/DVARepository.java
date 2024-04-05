package com.SoftTech.PayPlanet.modules.paystack.repository;

import com.SoftTech.PayPlanet.modules.paystack.model.DedicatedVirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVARepository extends JpaRepository<DedicatedVirtualAccount, Long> {
}
