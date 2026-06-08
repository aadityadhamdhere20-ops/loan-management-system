package com.lms.service;

import com.lms.dto.request.EmiPaymentRequest;
import com.lms.dto.response.ApiResponse;

public interface RepaymentService {

	ApiResponse pay(EmiPaymentRequest request);

	ApiResponse history(Long loanId);
}