package com.lms.controller;

import com.lms.dto.response.ApiResponse;
import com.lms.repository.EmiScheduleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emi")
public class EmiController {

	private final EmiScheduleRepository repo;

	// ✅ Manual constructor (IMPORTANT FIX)
	public EmiController(EmiScheduleRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/{loanId}")
	public Object list(@PathVariable Long loanId) {
		return new ApiResponse(true, "EMI list", repo.findByLoanAccountId(loanId));
	}
}