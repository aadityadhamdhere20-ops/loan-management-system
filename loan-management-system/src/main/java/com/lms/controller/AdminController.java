package com.lms.controller;

import com.lms.dto.request.LoanApprovalRequest;
import com.lms.service.impl.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/loan")
public class AdminController {

	private final AdminServiceImpl service;

	public AdminController(AdminServiceImpl service) {
		this.service = service;
	}

	@PostMapping("/approve/{id}")
	public Object approve(@PathVariable Long id, @RequestBody LoanApprovalRequest r) {
		return service.approve(id, r);
	}

	@PostMapping("/reject/{id}")
	public Object reject(@PathVariable Long id, @RequestParam String reason) {
		return service.reject(id, reason);
	}

	@PostMapping("/disburse/{applicationId}")
	public Object disburse(@PathVariable Long applicationId) {
		return service.disburse(applicationId);
	}
}