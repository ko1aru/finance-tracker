package com.local.FinanceTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.local.FinanceTracker.model.Emi;

@Repository
public interface EmiRepository extends JpaRepository<Emi, Long> {

}