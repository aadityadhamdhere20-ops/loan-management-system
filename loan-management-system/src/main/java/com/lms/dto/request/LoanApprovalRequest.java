package com.lms.dto.request;

import java.math.BigDecimal;

public class LoanApprovalRequest {

	private BigDecimal approvedAmount;
	private BigDecimal interestRate;

	public LoanApprovalRequest() {
	}

	public LoanApprovalRequest(BigDecimal approvedAmount, BigDecimal interestRate) {
		this.approvedAmount = approvedAmount;
		this.interestRate = interestRate;
	}

	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
}