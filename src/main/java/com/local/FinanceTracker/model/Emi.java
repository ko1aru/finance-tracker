package com.local.FinanceTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Emi {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private double emi;
	private String description;
	@ManyToOne
    @JoinColumn(name = "month_entry_id")
    private MonthEntry monthEntry;
	
	public Emi() {}
	public Emi(double emi, String desc) {
		this.emi = emi;
		this.description = (desc != null) ? desc : "";
	}
	public Emi(double emi) {
		this(emi, "");
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
	
	public double getEmi() {
		return emi;
	}
	
	public void setEmi(double emi) {
		this.emi = emi;
	}
	
	public void addEmi(double emi) {
		this.emi += emi;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
	    return "Emi{" +
	            "id=" + id +
	            ", emi=" + emi +
	            ", description='" + description + '\'' +
	            '}';
	}
}