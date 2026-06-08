package com.lms.controller;

import com.lms.dto.request.EmiPaymentRequest;
import com.lms.service.impl.RepaymentServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repayment")
public class RepaymentController {

	private final RepaymentServiceImpl service;

	// ✅ Manual constructor (fix)
	public RepaymentController(RepaymentServiceImpl service) {
		this.service = service;
	}

	@PostMapping("/pay")
	public Object pay(@RequestBody EmiPaymentRequest r) {
		return service.pay(r);
	}

	@GetMapping("/{loanId}")
	public Object history(@PathVariable Long loanId) {
		return service.history(loanId);
	}
}