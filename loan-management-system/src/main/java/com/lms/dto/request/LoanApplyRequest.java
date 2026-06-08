package com.lms.dto.request;

import java.math.BigDecimal;
import com.lms.enums.LoanType;

public class LoanApplyRequest {

	private Long userId;
	private LoanType loanType;
	private BigDecimal requestedAmount;
	private Integer tenureMonths;

	public LoanApplyRequest() {
	}

	public LoanApplyRequest(Long userId, LoanType loanType, BigDecimal requestedAmount, Integer tenureMonths) {
		this.userId = userId;
		this.loanType = loanType;
		this.requestedAmount = requestedAmount;
		this.tenureMonths = tenureMonths;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
}