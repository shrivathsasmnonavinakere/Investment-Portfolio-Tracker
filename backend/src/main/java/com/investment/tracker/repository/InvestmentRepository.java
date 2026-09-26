package com.investment.tracker.repository;

import com.investment.tracker.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
