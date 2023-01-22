package com.thetradingpit.msregister.repository;

import com.thetradingpit.msregister.model.entity.AffiliateClientMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AffiliateClientRepository extends JpaRepository<AffiliateClientMap, UUID> {
}
