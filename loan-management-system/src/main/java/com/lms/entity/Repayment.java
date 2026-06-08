package com.lms.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lms.enums.PaymentMode;
import com.lms.enums.PaymentType;

import jakarta.persistence.*;

@Entity
public class Repayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private LoanAccount loanAccount;

	@ManyToOne
	private EmiSchedule emi;

	private BigDecimal paidAmount;
	private LocalDate paymentDate;

	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;

	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	private String transactionId;

	public Repayment() {
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

	public EmiSchedule getEmi() {
		return emi;
	}

	public void setEmi(EmiSchedule emi) {
		this.emi = emi;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}