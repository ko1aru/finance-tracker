package com.local.FinanceTracker.service;

import java.util.*;

import com.local.FinanceTracker.dto.MonthEntryRequest;
import com.local.FinanceTracker.model.*;

public interface MonthEntryService {
	Map<String, MonthEntry> dataStore = new LinkedHashMap<>();
	
	public List<MonthEntry> getAllEntries();
	public MonthEntry getMonthEntry(int month, int year);
//	public void addMonthEntry(Time time, MonthEntry entry);
	public void addMonthEntry(MonthEntryRequest request);
	public void addExpense(MonthEntry entry, Expense expense);
	public boolean deleteExpense(MonthEntry entry, long expenseId);
	public void addEmi(MonthEntry entry, Emi emi);
	public boolean deleteEmi(MonthEntry entry, long emiId);
	public void updateFields(Map<String, Double> updatedFields, MonthEntry monthEntry);
}
