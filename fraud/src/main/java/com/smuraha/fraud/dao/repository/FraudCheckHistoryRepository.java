package com.smuraha.fraud.dao.repository;

import com.smuraha.fraud.dao.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory,Integer> {
}
