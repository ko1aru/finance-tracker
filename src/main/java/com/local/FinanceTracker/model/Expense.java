package com.local.FinanceTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Expense {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private double misc;
	private double fuel;
	private String description;
	private String date;
	@ManyToOne
    @JoinColumn(name = "month_entry_id")
    private MonthEntry monthEntry;

	public Expense() {}
	public Expense(double misc, double fuel) {
		this.misc = misc;
		this.fuel = fuel;
	}
	
	public void setMonthEntry(MonthEntry monthEntry) {
	    this.monthEntry = monthEntry;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getMisc() {
		return misc;
	}

	public void setMisc(double misc) {
		this.misc = misc;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}