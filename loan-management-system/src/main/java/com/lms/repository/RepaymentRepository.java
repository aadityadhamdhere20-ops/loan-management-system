package com.lms.repository;

import com.lms.entity.Repayment;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findByLoanAccountId(Long loanAccountId);
}
