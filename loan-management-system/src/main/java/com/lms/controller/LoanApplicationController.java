package com.lms.controller;

import com.lms.dto.request.LoanApplyRequest;
import com.lms.service.impl.LoanApplicationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
public class LoanApplicationController {

	private final LoanApplicationServiceImpl service;

	// ✅ Manual constructor (fix)
	public LoanApplicationController(LoanApplicationServiceImpl service) {
		this.service = service;
	}

	@PostMapping("/apply")
	public ResponseEntity<?> apply(@RequestBody LoanApplyRequest r) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.apply(r));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.get(id));
	}
}