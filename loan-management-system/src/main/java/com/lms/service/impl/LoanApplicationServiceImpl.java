package com.lms.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.lms.dto.request.LoanApplyRequest;
import com.lms.dto.response.ApiResponse;
import com.lms.entity.EligibilityCheck;
import com.lms.entity.LoanApplication;
import com.lms.entity.User;
import com.lms.enums.LoanStatus;
import com.lms.repository.EligibilityCheckRepository;
import com.lms.repository.LoanApplicationRepository;
import com.lms.repository.UserRepository;
import com.lms.service.LoanApplicationService;
import com.lms.util.EmiCalculator;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private final LoanApplicationRepository loanRepo;
	private final UserRepository userRepo;
	private final EligibilityCheckRepository eligibilityRepo;

	public LoanApplicationServiceImpl(LoanApplicationRepository loanRepo, UserRepository userRepo,
			EligibilityCheckRepository eligibilityRepo) {
		this.loanRepo = loanRepo;
		this.userRepo = userRepo;
		this.eligibilityRepo = eligibilityRepo;
	}

	@Override
	public ApiResponse apply(LoanApplyRequest r) {

		User u = userRepo.findById(r.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

		BigDecimal rate = BigDecimal.valueOf(12);

		BigDecimal emi = EmiCalculator.calculate(r.getRequestedAmount(), rate, r.getTenureMonths());

		LoanApplication app = new LoanApplication();
		app.setUser(u);
		app.setLoanType(r.getLoanType());
		app.setRequestedAmount(r.getRequestedAmount());
		app.setTenureMonths(r.getTenureMonths());
		app.setInterestRate(rate);
		app.setCalculatedEmi(emi);
		app.setApplicationStatus(LoanStatus.SUBMITTED);
		app.setLoanApplicationSubmitted(true);

		loanRepo.save(app);

		checkEligibility(app);

		return new ApiResponse(true, "Loan applied successfully", app.getId());
	}

	public EligibilityCheck checkEligibility(LoanApplication app) {

		User u = app.getUser();

		boolean age = u.getAge() != null && u.getAge() >= 21 && u.getAge() <= 60;

		boolean cibil = true;

		boolean foir = u.getMonthlyIncome() != null
				&& app.getCalculatedEmi().compareTo(u.getMonthlyIncome().multiply(BigDecimal.valueOf(0.5))) <= 0;

		boolean defaulted = u.isDefaulted();

		boolean eligible = age && cibil && foir && !defaulted;

		EligibilityCheck e = new EligibilityCheck();
		e.setApplication(app);
		e.setAgeEligible(age);
		e.setCibilEligible(cibil);
		e.setFoirEligible(foir);
		e.setDefaulted(defaulted);
		e.setFinalEligible(eligible);
		e.setSimulatedCibilScore(750);

		return eligibilityRepo.save(e);
	}

	@Override
	public ApiResponse get(Long id) {
		LoanApplication app = loanRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan application not found"));

		return new ApiResponse(true, "Loan details", app);
	}
}