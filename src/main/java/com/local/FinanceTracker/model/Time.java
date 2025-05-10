package com.local.FinanceTracker.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Time {
	private int month;
	private int year;
	
	public Time() {}
	public Time(int month, int year) {
		this.month = month;
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String toKey() {
		return month + "-" + year;
	}
}