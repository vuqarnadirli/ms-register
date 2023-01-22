package com.thetradingpit.msregister.repository;

import com.thetradingpit.msregister.model.entity.AffiliateTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateTransactionRepository extends JpaRepository<AffiliateTransactions, Long> {
}
