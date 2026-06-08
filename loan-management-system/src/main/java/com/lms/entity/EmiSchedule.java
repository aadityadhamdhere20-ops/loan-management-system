package com.lms.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lms.enums.EmiStatus;

import jakarta.persistence.*;

@Entity
public class EmiSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private LoanAccount loanAccount;

	private Integer emiNumber;
	private LocalDate dueDate;
	private BigDecimal emiAmount;
	private BigDecimal penaltyAmount;
	private BigDecimal totalPayableAmount;

	@Enumerated(EnumType.STRING)
	private EmiStatus emiStatus;

	private boolean penaltyApplied;

	public EmiSchedule() {
	}

	public Long getId() {
		return id;
	}

	public LoanAccount getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(LoanAccount loanAccount) {
		this.loanAccount = loanAccount;
	}

	public Integer getEmiNumber() {
		return emiNumber;
	}

	public void setEmiNumber(Integer emiNumber) {
		this.emiNumber = emiNumber;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}

	public BigDecimal getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(BigDecimal penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public BigDecimal getTotalPayableAmount() {
		return totalPayableAmount;
	}

	public void setTotalPayableAmount(BigDecimal totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}

	public EmiStatus getEmiStatus() {
		return emiStatus;
	}

	public void setEmiStatus(EmiStatus emiStatus) {
		this.emiStatus = emiStatus;
	}

	public boolean isPenaltyApplied() {
		return penaltyApplied;
	}

	public void setPenaltyApplied(boolean penaltyApplied) {
		this.penaltyApplied = penaltyApplied;
	}
}