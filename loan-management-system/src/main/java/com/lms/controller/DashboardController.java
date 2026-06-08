package com.lms.controller;

import java.math.BigDecimal;

import com.lms.dto.response.DashboardResponse;
import com.lms.entity.LoanAccount;
import com.lms.enums.LoanStatus;
import com.lms.repository.LoanAccountRepository;
import com.lms.repository.LoanApplicationRepository;
import com.lms.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private final UserRepository userRepo;
	private final LoanApplicationRepository loanRepo;
	private final LoanAccountRepository accRepo;

	public DashboardController(UserRepository userRepo, LoanApplicationRepository loanRepo,
			LoanAccountRepository accRepo) {
		this.userRepo = userRepo;
		this.loanRepo = loanRepo;
		this.accRepo = accRepo;
	}

	@GetMapping("/admin")
	public DashboardResponse admin() {

		BigDecimal totalAmount = BigDecimal.ZERO;

		for (LoanAccount account : accRepo.findAll()) {
			if (account.getLoanAmount() != null) {
				totalAmount = totalAmount.add(account.getLoanAmount());
			}
		}

		DashboardResponse response = new DashboardResponse();
		response.setTotalUsers(userRepo.count());
		response.setTotalApplications(loanRepo.count());
		response.setPendingApprovals(loanRepo.findByApplicationStatus(LoanStatus.SUBMITTED).size());
		response.setTotalDisbursedAmount(totalAmount);

		return response;
	}

	@GetMapping("/officer")
	public String officer() {
		return "Officer dashboard API";
	}
}