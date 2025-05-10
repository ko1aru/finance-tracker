package com.local.FinanceTracker.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.FinanceTracker.constants.Constants;
import com.local.FinanceTracker.dto.MonthEntryRequest;
import com.local.FinanceTracker.model.*;
import com.local.FinanceTracker.repo.*;
import com.local.FinanceTracker.service.MonthEntryService;

@Service
public class MonthEntryServiceImpl implements MonthEntryService {
	
	@Autowired
	private MonthEntryRepository monthEntryRepository;
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private EmiRepository emiRepository;
	
	public List<MonthEntry> getAllEntries() {
		return monthEntryRepository.findAll();
	}
	public MonthEntry getMonthEntry(int month, int year) {
	    return monthEntryRepository.findAll().stream()
	        .filter(entry -> entry.getMonth() == month && entry.getYear() == year)
	        .findFirst()
	        .orElse(null);
	}
	
	public void addMonthEntry(MonthEntryRequest request) {
		int prevMonth = request.getMonth() == 1 ? 12 : request.getMonth() - 1;
	    int prevYear = request.getMonth() == 1 ? request.getYear() - 1 : request.getYear();
	    MonthEntry prevMonthEntry = getMonthEntry(prevMonth, prevYear);
		double prevAccBalance = prevMonthEntry != null ? prevMonthEntry.getAccBalance() : 0;
		
		MonthEntry currMonth = request.toMonthEntry(prevAccBalance);
	    monthEntryRepository.save(currMonth);
	}
	
	public void addExpense(MonthEntry entry, Expense expense) {
		if (entry == null || expense == null) return;
		entry.addExpense(expense);
	    monthEntryRepository.save(entry);
	}
	
	public boolean deleteExpense(MonthEntry entry, long expenseId) {
		if (entry == null) return false;
		Optional<Expense> optional = expenseRepository.findById(expenseId);
		if(optional.isPresent()) {
			entry.deleteExpense(optional.get());
			monthEntryRepository.save(entry);
			expenseRepository.deleteById(expenseId);
			return true;
		}
		return false;
	}
	
	public void addEmi(MonthEntry entry, Emi emi) {
		if (entry == null || emi == null) return;
		entry.addEmi(emi);
	    monthEntryRepository.save(entry);
	}
	
	public boolean deleteEmi(MonthEntry entry, long emiId) {
		if (entry == null) return false;
		Optional<Emi> optional = emiRepository.findById(emiId);
		if(optional.isPresent()) {
			entry.deleteEmi(optional.get());
			monthEntryRepository.save(entry);
			emiRepository.deleteById(emiId);
			return true;
		}
		return false;
	}
	
	public void updateFields(Map<String, Double> updatedFields, MonthEntry monthEntry) {
		if(updatedFields.containsKey(Constants.SALARY)) {
			monthEntry.setSalary(updatedFields.get(Constants.SALARY));
		}
		if(updatedFields.containsKey(Constants.SIDE_INCOME)) {
			monthEntry.setSideIncome(updatedFields.get(Constants.SIDE_INCOME));
		}
		if(updatedFields.containsKey(Constants.HEALTH_INSURANCE)) {
			monthEntry.setHealthInsurance(updatedFields.get(Constants.HEALTH_INSURANCE));
		}
		if(updatedFields.containsKey(Constants.EMERGENCY_ACC)) {
			monthEntry.setEmergencyAcc(updatedFields.get(Constants.EMERGENCY_ACC));
		}
		
		monthEntry.updateFields();
		monthEntryRepository.save(monthEntry);
	}
}