package com.thetradingpit.msregister.repository;

import com.thetradingpit.msregister.model.entity.FailedCalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface FailedCallsRepository extends JpaRepository<FailedCalls, Id> {
}
