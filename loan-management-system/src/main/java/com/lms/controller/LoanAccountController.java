package com.lms.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loanaccount")
public class LoanAccountController {
	@GetMapping
	public String home() {
		return "LoanAccountController working";
	}
}