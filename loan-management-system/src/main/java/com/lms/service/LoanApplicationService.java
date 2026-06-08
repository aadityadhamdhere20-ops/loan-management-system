package com.lms.service;

import com.lms.dto.request.LoanApplyRequest;
import com.lms.dto.response.ApiResponse;

public interface LoanApplicationService {

	ApiResponse apply(LoanApplyRequest request);

	ApiResponse get(Long id);
}