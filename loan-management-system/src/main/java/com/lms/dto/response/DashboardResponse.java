package com.lms.dto.response;

import java.math.BigDecimal;

public class DashboardResponse {

	private Long totalUsers;
	private Long totalApplications;
	private Integer pendingApprovals;
	private BigDecimal totalDisbursedAmount;

	public DashboardResponse() {
		super();
	}

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Long getTotalApplications() {
		return totalApplications;
	}

	public void setTotalApplications(Long totalApplications) {
		this.totalApplications = totalApplications;
	}

	public Integer getPendingApprovals() {
		return pendingApprovals;
	}

	public void setPendingApprovals(Integer pendingApprovals) {
		this.pendingApprovals = pendingApprovals;
	}

	public BigDecimal getTotalDisbursedAmount() {
		return totalDisbursedAmount;
	}

	public void setTotalDisbursedAmount(BigDecimal totalDisbursedAmount) {
		this.totalDisbursedAmount = totalDisbursedAmount;
	}
}