package com.SoftTech.PayPlanet.modules.user.repository;

import com.SoftTech.PayPlanet.modules.user.model.PlanetUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanetUserRepository extends JpaRepository<PlanetUser, Long> {
    PlanetUser findByEmailAddress(String emailAddress);
    PlanetUser findByUsername(String username);
    PlanetUser findByMobileNumber(String mobileNumber);
}
