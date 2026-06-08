package com.lms.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lms.enums.LoanStatus;
import com.lms.enums.LoanType;

import jakarta.persistence.*;

@Entity
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@Enumerated(EnumType.STRING)
	private LoanType loanType;

	private BigDecimal requestedAmount;
	private Integer tenureMonths;
	private BigDecimal interestRate;
	private BigDecimal calculatedEmi;

	@Enumerated(EnumType.STRING)
	private LoanStatus applicationStatus;

	@Column(columnDefinition = "TEXT")
	private String rejectionReason;

	private boolean loanApplicationSubmitted;
	private LocalDateTime appliedAt;
	private LocalDateTime lastUpdatedAt;

	// ✅ Constructors
	public LoanApplication() {
	}

	public LoanApplication(Long id, User user, LoanType loanType, BigDecimal requestedAmount, Integer tenureMonths,
			BigDecimal interestRate, BigDecimal calculatedEmi, LoanStatus applicationStatus, String rejectionReason,
			boolean loanApplicationSubmitted, LocalDateTime appliedAt, LocalDateTime lastUpdatedAt) {
		this.id = id;
		this.user = user;
		this.loanType = loanType;
		this.requestedAmount = requestedAmount;
		this.tenureMonths = tenureMonths;
		this.interestRate = interestRate;
		this.calculatedEmi = calculatedEmi;
		this.applicationStatus = applicationStatus;
		this.rejectionReason = rejectionReason;
		this.loanApplicationSubmitted = loanApplicationSubmitted;
		this.appliedAt = appliedAt;
		this.lastUpdatedAt = lastUpdatedAt;
	}

	// ✅ PrePersist
	@PrePersist
	public void pre() {
		appliedAt = LocalDateTime.now();
		lastUpdatedAt = appliedAt;

		if (applicationStatus == null) {
			applicationStatus = LoanStatus.DRAFT;
		}
	}

	// ✅ PreUpdate
	@PreUpdate
	public void upd() {
		lastUpdatedAt = LocalDateTime.now();
	}

	// ✅ Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public Integer getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(Integer tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getCalculatedEmi() {
		return calculatedEmi;
	}

	public void setCalculatedEmi(BigDecimal calculatedEmi) {
		this.calculatedEmi = calculatedEmi;
	}

	public LoanStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(LoanStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public boolean isLoanApplicationSubmitted() {
		return loanApplicationSubmitted;
	}

	public void setLoanApplicationSubmitted(boolean loanApplicationSubmitted) {
		this.loanApplicationSubmitted = loanApplicationSubmitted;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

	public LocalDateTime getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
}