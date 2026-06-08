package com.lms.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lms.enums.AccountStatus;

import jakarta.persistence.*;

@Entity
public class LoanAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private LoanApplication application;

	@ManyToOne
	private User user;

	private BigDecimal loanAmount;
	private BigDecimal interestRate;
	private Integer tenureMonths;
	private BigDecimal emiAmount;
	private BigDecimal outstandingBalance;

	@Enumerated(EnumType.STRING)
	private AccountStatus loanAccountStatus;

	private String closureType;
	private LocalDate disbursedDate;
	private LocalDate closureDate;

	// ✅ Constructors
	public LoanAccount() {
	}

	public LoanAccount(Long id, LoanApplication application, User user, BigDecimal loanAmount, BigDecimal interestRate,
			Integer tenureMonths, BigDecimal emiAmount, BigDecimal outstandingBalance, AccountStatus loanAccountStatus,
			String closureType, LocalDate disbursedDate, LocalDate closureDate) {
		this.id = id;
		this.application = application;
		this.user = user;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.tenureMonths = tenureMonths;
		this.emiAmount = emiAmount;
		this.outstandingBalance = outstandingBalance;
		this.loanAccountStatus = loanAccountStatus;
		this.closureType = closureType;
		this.disbursedDate = disbursedDate;
		this.closureDate = closureDate;
	}

	// ✅ Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplication getApplication() {
		return application;
	}

	public void setApplication(LoanApplication application) {
		this.application = application;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Integer getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(Integer tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public BigDecimal getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}

	public BigDecimal getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(BigDecimal outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public AccountStatus getLoanAccountStatus() {
		return loanAccountStatus;
	}

	public void setLoanAccountStatus(AccountStatus loanAccountStatus) {
		this.loanAccountStatus = loanAccountStatus;
	}

	public String getClosureType() {
		return closureType;
	}

	public void setClosureType(String closureType) {
		this.closureType = closureType;
	}

	public LocalDate getDisbursedDate() {
		return disbursedDate;
	}

	public void setDisbursedDate(LocalDate disbursedDate) {
		this.disbursedDate = disbursedDate;
	}

	public LocalDate getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(LocalDate closureDate) {
		this.closureDate = closureDate;
	}
}