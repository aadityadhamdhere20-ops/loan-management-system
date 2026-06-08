package com.lms.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.lms.dto.request.LoanApprovalRequest;
import com.lms.dto.response.ApiResponse;
import com.lms.entity.EmiSchedule;
import com.lms.entity.LoanAccount;
import com.lms.entity.LoanApplication;
import com.lms.entity.SanctionLetter;
import com.lms.enums.AccountStatus;
import com.lms.enums.EmiStatus;
import com.lms.enums.LoanStatus;
import com.lms.repository.EmiScheduleRepository;
import com.lms.repository.LoanAccountRepository;
import com.lms.repository.LoanApplicationRepository;
import com.lms.repository.SanctionLetterRepository;
import com.lms.service.AdminService;
import com.lms.util.EmiCalculator;

@Service
public class AdminServiceImpl implements AdminService {

	private final LoanApplicationRepository loanRepo;
	private final SanctionLetterRepository sanctionRepo;
	private final LoanAccountRepository accountRepo;
	private final EmiScheduleRepository emiRepo;

	public AdminServiceImpl(LoanApplicationRepository loanRepo, SanctionLetterRepository sanctionRepo,
			LoanAccountRepository accountRepo, EmiScheduleRepository emiRepo) {
		this.loanRepo = loanRepo;
		this.sanctionRepo = sanctionRepo;
		this.accountRepo = accountRepo;
		this.emiRepo = emiRepo;
	}

	@Override
	public ApiResponse approve(Long id, LoanApprovalRequest r) {

		LoanApplication a = loanRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan application not found"));

		a.setApplicationStatus(LoanStatus.APPROVED);
		loanRepo.save(a);

		BigDecimal emiAmount = EmiCalculator.calculate(r.getApprovedAmount(), r.getInterestRate(), a.getTenureMonths());

		SanctionLetter s = new SanctionLetter();
		s.setApplication(a);
		s.setApprovedAmount(r.getApprovedAmount());
		s.setInterestRate(r.getInterestRate());
		s.setTenureMonths(a.getTenureMonths());
		s.setEmiAmount(emiAmount);
		s.setStatus("SENT");
		s.setValidTill(LocalDate.now().plusDays(7));
		s.setSentAt(LocalDateTime.now());

		sanctionRepo.save(s);

		return new ApiResponse(true, "Loan approved and sanction generated", s.getId());
	}

	@Override
	public ApiResponse reject(Long id, String reason) {

		LoanApplication a = loanRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan application not found"));

		a.setApplicationStatus(LoanStatus.REJECTED);
		a.setRejectionReason(reason);

		loanRepo.save(a);

		return new ApiResponse(true, "Loan rejected", null);
	}

	@Override
	public ApiResponse disburse(Long appId) {

		LoanApplication a = loanRepo.findById(appId)
				.orElseThrow(() -> new RuntimeException("Loan application not found"));

		LoanAccount acc = new LoanAccount();
		acc.setApplication(a);
		acc.setUser(a.getUser());
		acc.setLoanAmount(a.getRequestedAmount());
		acc.setInterestRate(a.getInterestRate());
		acc.setTenureMonths(a.getTenureMonths());
		acc.setEmiAmount(a.getCalculatedEmi());
		acc.setOutstandingBalance(a.getRequestedAmount());
		acc.setLoanAccountStatus(AccountStatus.ACTIVE);
		acc.setDisbursedDate(LocalDate.now());

		accountRepo.save(acc);

		for (int i = 1; i <= a.getTenureMonths(); i++) {

			EmiSchedule emi = new EmiSchedule();
			emi.setLoanAccount(acc);
			emi.setEmiNumber(i);
			emi.setDueDate(LocalDate.now().plusMonths(i));
			emi.setEmiAmount(a.getCalculatedEmi());
			emi.setPenaltyAmount(BigDecimal.ZERO);
			emi.setTotalPayableAmount(a.getCalculatedEmi());
			emi.setEmiStatus(EmiStatus.PENDING);

			emiRepo.save(emi);
		}

		a.setApplicationStatus(LoanStatus.ACTIVE);
		loanRepo.save(a);

		return new ApiResponse(true, "Loan disbursed and EMI schedule generated", acc.getId());
	}
}