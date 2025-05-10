package com.local.FinanceTracker.dto;

import com.local.FinanceTracker.model.*;

public class MonthEntryRequest {
	private int month;
    private int year;
    private double salary;
    private double sideIncome;
    private double healthInsurance;
    private double emergencyAcc;
    private double stockInv;
    private double expenseAcc;
    
    private Time getTime() {
    	return new Time(month, year);
    }
    
    public MonthEntry toMonthEntry(double prevAccBalance) {
    	MonthEntry monthEntry = new MonthEntry(month, year, salary, sideIncome, healthInsurance, emergencyAcc, expenseAcc, stockInv, prevAccBalance);
    	return monthEntry;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getSideIncome() {
		return sideIncome;
	}

	public void setSideIncome(double sideIncome) {
		this.sideIncome = sideIncome;
	}

	public double getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(double healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public double getEmergencyAcc() {
		return emergencyAcc;
	}

	public void setEmergencyAcc(double emergencyAcc) {
		this.emergencyAcc = emergencyAcc;
	}

	public double getStockInv() {
		return stockInv;
	}

	public void setStockInv(double stockInv) {
		this.stockInv = stockInv;
	}

	public double getExpenseAcc() {
		return expenseAcc;
	}

	public void setExpenseAcc(double expenseAcc) {
		this.expenseAcc = expenseAcc;
	}

	@Override
	public String toString() {
		return "MonthEntryRequest [month=" + month + ", year=" + year + ", salary=" + salary + ", sideIncome="
				+ sideIncome + ", healthInsurance=" + healthInsurance + ", emergencyAcc=" + emergencyAcc + 
				", stockInv=" + stockInv + ", expenseAcc=" + expenseAcc + "]";
	}
}