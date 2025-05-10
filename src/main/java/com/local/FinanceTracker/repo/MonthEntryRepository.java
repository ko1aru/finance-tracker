package com.local.FinanceTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.local.FinanceTracker.model.MonthEntry;

@Repository
public interface MonthEntryRepository extends JpaRepository<MonthEntry, Long> {
	
}