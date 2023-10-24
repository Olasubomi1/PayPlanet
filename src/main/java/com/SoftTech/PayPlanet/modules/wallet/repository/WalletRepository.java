package com.SoftTech.PayPlanet.modules.wallet.repository;

import com.SoftTech.PayPlanet.modules.wallet.model.PlanetWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<PlanetWallet, Long> {
}
