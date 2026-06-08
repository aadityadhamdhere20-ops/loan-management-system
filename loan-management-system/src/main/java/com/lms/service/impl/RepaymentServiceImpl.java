package com.lms.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.dto.request.EmiPaymentRequest;
import com.lms.dto.response.ApiResponse;
import com.lms.entity.EmiSchedule;
import com.lms.entity.LoanAccount;
import com.lms.entity.Repayment;
import com.lms.enums.AccountStatus;
import com.lms.enums.EmiStatus;
import com.lms.enums.PaymentType;
import com.lms.repository.EmiScheduleRepository;
import com.lms.repository.LoanAccountRepository;
import com.lms.repository.RepaymentRepository;
import com.lms.service.RepaymentService;
import com.lms.util.TransactionIdGenerator;

@Service
public class RepaymentServiceImpl implements RepaymentService {

	private final LoanAccountRepository accountRepo;
	private final EmiScheduleRepository emiRepo;
	private final RepaymentRepository repaymentRepo;

	public RepaymentServiceImpl(LoanAccountRepository accountRepo,
			EmiScheduleRepository emiRepo,
			RepaymentRepository repaymentRepo) {
		this.accountRepo = accountRepo;
		this.emiRepo = emiRepo;
		this.repaymentRepo = repaymentRepo;
	}

	@Override
	public ApiResponse pay(EmiPaymentRequest r) {

		LoanAccount acc = accountRepo.findById(r.getLoanAccountId())
				.orElseThrow(() -> new RuntimeException("Loan account not found"));

		EmiSchedule emi = emiRepo.findById(r.getEmiId())
				.orElseThrow(() -> new RuntimeException("EMI not found"));

		if (acc.getLoanAccountStatus() == AccountStatus.CLOSED) {
			return new ApiResponse(false, "Loan already closed", null);
		}

		if (emi.getEmiStatus() == EmiStatus.PAID) {
			return new ApiResponse(false, "This EMI is already paid", null);
		}

		emi.setEmiStatus(EmiStatus.PAID);
		emiRepo.save(emi);

		BigDecimal remainingBalance = acc.getOutstandingBalance()
				.subtract(r.getPaidAmount())
				.max(BigDecimal.ZERO);

		acc.setOutstandingBalance(remainingBalance);

		if (remainingBalance.compareTo(BigDecimal.ZERO) == 0) {

			acc.setLoanAccountStatus(AccountStatus.CLOSED);
			acc.setClosureDate(LocalDate.now());
			acc.setClosureType("NORMAL");

			List<EmiSchedule> emiList = emiRepo.findByLoanAccountId(acc.getId());

			for (EmiSchedule e : emiList) {
				if (e.getEmiStatus() == EmiStatus.PENDING || e.getEmiStatus() == EmiStatus.OVERDUE) {
					e.setEmiStatus(EmiStatus.FORECLOSED);
					emiRepo.save(e);
				}
			}
		}

		accountRepo.save(acc);

		Repayment pay = new Repayment();
		pay.setLoanAccount(acc);
		pay.setEmi(emi);
		pay.setPaidAmount(r.getPaidAmount());
		pay.setPaymentDate(LocalDate.now());
		pay.setPaymentMode(r.getPaymentMode());
		pay.setPaymentType(PaymentType.EMI);
		pay.setTransactionId(TransactionIdGenerator.generate());

		repaymentRepo.save(pay);

		return new ApiResponse(true, "EMI paid successfully", pay.getTransactionId());
	}

	@Override
	public ApiResponse history(Long loanId) {
		return new ApiResponse(true, "Payment history", repaymentRepo.findByLoanAccountId(loanId));
	}
}