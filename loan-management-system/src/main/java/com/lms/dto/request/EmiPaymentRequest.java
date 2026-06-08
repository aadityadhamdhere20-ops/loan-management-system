package com.lms.dto.request;

import java.math.BigDecimal;
import com.lms.enums.PaymentMode;

public class EmiPaymentRequest {

	private Long loanAccountId;
	private Long emiId;
	private BigDecimal paidAmount;
	private PaymentMode paymentMode;

	public EmiPaymentRequest() {
	}

	public Long getLoanAccountId() {
		return loanAccountId;
	}

	public void setLoanAccountId(Long loanAccountId) {
		this.loanAccountId = loanAccountId;
	}

	public Long getEmiId() {
		return emiId;
	}

	public void setEmiId(Long emiId) {
		this.emiId = emiId;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
}