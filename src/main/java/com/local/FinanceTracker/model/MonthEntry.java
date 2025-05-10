package com.local.FinanceTracker.model;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MonthEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private int month;
	private int year;
	private double salary;
	private double sideIncome;
	private double healthInsurance;
	private double emergencyAcc;
	private double emi;
	private double expenseAcc;
	private double misc;
	private double fuel;
	private double expenseAccBalance;
	private double stockInv;
	private double amtLeft;
	private double accBalance;
	private double prevAccBalance;
	@OneToMany(mappedBy = "monthEntry", cascade = CascadeType.ALL)
	private List<Expense> expenseList = new ArrayList<>();
	@OneToMany(mappedBy = "monthEntry", cascade = CascadeType.ALL)
	private List<Emi> emiList = new ArrayList<>();
	
	public MonthEntry() {}
	public MonthEntry(int month, int year, double salary, double sideIncome, double healthInsurance, double emergencyAcc,
			double expenseAcc, double stockInv, double prevAccBalance) {
		this.month = month;
		this.year = year;
		this.salary = salary;
		this.sideIncome = sideIncome;
		this.healthInsurance = healthInsurance;
		this.emergencyAcc = emergencyAcc;
		this.emi = 0;
		this.expenseAcc = expenseAcc;
		this.misc = 0;
		this.fuel = 0;
		this.expenseAccBalance = expenseAcc - (this.misc + this.fuel);
		this.stockInv = stockInv;
		this.prevAccBalance = prevAccBalance;
		updateFields();
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

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}
	
	public void addEmi(Emi emi) {
		if (emi == null) return;
		emi.setMonthEntry(this);
		this.emi += emi.getEmi();
		this.emiList.add(emi);
		updateFields();
	}
	
	public void deleteEmi(Emi emi) {
		if (emi == null) return;
		this.emi -= emi.getEmi();
		this.emiList.remove(emi);
		updateFields();
	}

	public List<Emi> getEmiList() {
		return emiList;
	}

	public void setEmiList(List<Emi> emiList) {
		this.emiList = emiList;
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

	public double getExpenseAccBalance() {
		return expenseAccBalance;
	}

	public void setExpenseAccBalance(double expenseAccBalance) {
		this.expenseAccBalance = expenseAccBalance;
	}

	public double getExpenseAcc() {
		return expenseAcc;
	}

	public void setExpenseAcc(double expenseAcc) {
		this.expenseAcc = expenseAcc;
	}

	public double getStockInv() {
		return stockInv;
	}

	public void setStockInv(double stockInv) {
		this.stockInv = stockInv;
	}

	public double getAmtLeft() {
		return amtLeft;
	}

	public void setAmtLeft() {
		this.amtLeft = (salary + sideIncome) - (healthInsurance + emergencyAcc + emi + misc + fuel + stockInv);
	}
	public void setAmtLeft(double amtLeft) {
		this.amtLeft = amtLeft;
	}

	public double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance() {
		this.accBalance = getPrevAccBalance() + getAmtLeft();
	}

	public List<Expense> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<Expense> expenses) {
		this.expenseList = expenses;
	}
	
	public void addExpense(Expense expense) {
		expense.setMonthEntry(this);
		misc += expense.getMisc();
		fuel += expense.getFuel();
		updateExpenseAccBalance();
		this.expenseList.add(expense);
		updateFields();
	}
	
	public void deleteExpense(Expense expense) {
		misc -= expense.getMisc();
		fuel -= expense.getFuel();
		updateExpenseAccBalance();
		this.expenseList.remove(expense);
		updateFields();
	}
	
	public void updateExpenseAccBalance() {
		this.expenseAccBalance = this.expenseAcc - (this.misc + this.fuel);
	}

	public double getPrevAccBalance() {
		return prevAccBalance;
	}

	public void setPrevAccBalance(double prevAccBalance) {
		this.prevAccBalance = prevAccBalance;
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
	
	public void updateFields() {
		setAmtLeft();
		setAccBalance();
	}
	
}