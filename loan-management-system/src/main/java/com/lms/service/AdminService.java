package com.lms.service;

import com.lms.dto.request.LoanApprovalRequest;
import com.lms.dto.response.ApiResponse;

public interface AdminService {

	ApiResponse approve(Long id, LoanApprovalRequest request);

	ApiResponse reject(Long id, String reason);

	ApiResponse disburse(Long appId);
}