package com.local.FinanceTracker.model;

public class ExpenseAccount {
	private double expenseAcc;
	private double misc;
	private double fuel;
	private double expenseBalance;
	
	public ExpenseAccount() {}
	public ExpenseAccount(double expenseAcc) {
		this.expenseAcc = expenseAcc;
		this.misc = 0;
		this.fuel = 0;
		setExpenseBalance();
	}
	public ExpenseAccount(Expense expense) {
		this.misc = expense.getMisc();
		this.fuel = expense.getFuel();
		setExpenseBalance();
	}
	
	public double getExpenseAcc() {
		return expenseAcc;
	}

	public void setExpenseAcc(double expenseAcc) {
		this.expenseAcc = expenseAcc;
	}

	public double getMisc() {
		return misc;
	}
	
	public void setMisc(double misc) {
		this.misc = misc;
		setExpenseBalance();
	}
	
	public double getFuel() {
		return fuel;
	}
	
	public void setFuel(double fuel) {
		this.fuel = fuel;
		setExpenseBalance();
	}
	
	public double getExpenseBalance() {
		return expenseBalance;
	}
	
	public void setExpenseBalance() {
		this.expenseBalance = this.expenseAcc - (this.misc + this.fuel);
	}
	
	public void setExpenseBalance(double expenseBalance) {
		this.expenseBalance = expenseBalance;
	}
	
	public void addExpense(double misc, double fuel) {
		this.misc += misc;
		this.fuel += fuel;
		setExpenseBalance();
	}
}
