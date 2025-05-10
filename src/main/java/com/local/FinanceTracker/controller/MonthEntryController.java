package com.local.FinanceTracker.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.local.FinanceTracker.dto.MonthEntryRequest;
import com.local.FinanceTracker.model.*;
import com.local.FinanceTracker.service.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MonthEntryController {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthEntryController.class);

	private final MonthEntryService service;
	
	@Autowired
	public MonthEntryController(MonthEntryService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<MonthEntry> getAll() {
	    logger.info("Fetching all month entries");
		return service.getAllEntries();
	}
	
	@GetMapping("/{month}-{year}")
	public MonthEntry getByMonthYear(@PathVariable int month, @PathVariable int year) {
	    logger.info("Fetching a month entry: Month-" + month + ", Year-" + year);
		return service.getMonthEntry(month, year);
	}
	
	@PostMapping
	public ResponseEntity<String> addMonthEntry(@RequestBody MonthEntryRequest request) {
		logger.info("Adding a Month Entry: \n" + request.toString());
		service.addMonthEntry(request);
		return ResponseEntity.ok("Month entry added successfully!");
	}
	
	@PutMapping("/{month}-{year}/update-fields")
	public ResponseEntity<String> updateFields(@PathVariable int month, @PathVariable int year, @RequestBody Map<String, Double> updatedFields) {
		MonthEntry monthEntry = service.getMonthEntry(month, year);
        if (monthEntry == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MonthEntry not found.");
        
        service.updateFields(updatedFields, monthEntry);
        return ResponseEntity.ok("Fields updated succesfully!");
	}
	
	@PostMapping("/{month}-{year}/add-expense")
	public ResponseEntity<String> addExpense(@PathVariable int month, @PathVariable int year, @RequestBody Expense expense) {
		MonthEntry monthEntry = service.getMonthEntry(month, year);
        if (monthEntry == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MonthEntry not found.");
        
        logger.info("Adding an Expense");
		service.addExpense(monthEntry, expense);
		return ResponseEntity.ok("Expense added succesfully!");
	}
	
	@DeleteMapping("/{month}-{year}/delete-expense/{expenseId}")
	public ResponseEntity<String> deleteExpense(@PathVariable int month, @PathVariable int year, @PathVariable long expenseId) {
		logger.info("Fetching an Expense: Month-" + month + ", Year-" + year + ", Id-" + expenseId);
		MonthEntry monthEntry = service.getMonthEntry(month, year);
		if (monthEntry == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MonthEntry not found.");
		
		logger.info("Deleting an Expense");
		if(service.deleteExpense(monthEntry, expenseId))
			return ResponseEntity.ok("Emi deleted successfully!");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EMI not found in the MonthEntry.");
	}
	
	@GetMapping("/{month}-{year}/expenses")
	public List<Expense> getExpenses(@PathVariable int month, @PathVariable int year) {
		logger.info("Fetching an expense: Month-" + month + ", Year-" + year);
		MonthEntry monthEntry = service.getMonthEntry(month, year);
		return monthEntry != null ? monthEntry.getExpenseList() : Collections.emptyList();
	}
	
	@PostMapping("/{month}-{year}/add-emi")
	public ResponseEntity<String> addEmi(@PathVariable int month, @PathVariable int year, @RequestBody Emi emi) {
		MonthEntry monthEntry = service.getMonthEntry(month, year);
        if (monthEntry == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MonthEntry not found.");
        
        logger.info("Adding an Emi");
		service.addEmi(monthEntry, emi);
		return ResponseEntity.ok("Emi added successfully!");
	}
	
	@DeleteMapping("/{month}-{year}/delete-emi/{emiId}")
	public ResponseEntity<String> deleteEmi(@PathVariable int month, @PathVariable int year, @PathVariable long emiId) {
		logger.info("Fetching an EMI: Month-" + month + ", Year-" + year + ", Id-" + emiId);
		MonthEntry monthEntry = service.getMonthEntry(month, year);
		if (monthEntry == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MonthEntry not found.");
		
		logger.info("Deleting an Emi");
		if(service.deleteEmi(monthEntry, emiId))
			return ResponseEntity.ok("Emi deleted successfully!");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EMI not found in the MonthEntry.");
	}
	
	@GetMapping("/{month}-{year}/emis")
	public List<Emi> getEmi(@PathVariable int month, @PathVariable int year) {
		logger.info("Fetching an EMI: Month-" + month + ", Year-" + year);
		MonthEntry monthEntry = service.getMonthEntry(month, year);
		logger.info(monthEntry.getEmiList().toString());
		return monthEntry != null ? monthEntry.getEmiList() : Collections.emptyList();
	}
}