package com.lms.repository;

import com.lms.entity.LoanAccount;
import java.util.*;import com.lms.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, Long> {
    List<LoanAccount> findByUserId(Long userId); Optional<LoanAccount> findByApplicationId(Long applicationId); boolean existsByUserIdAndLoanAccountStatus(Long userId, AccountStatus status);
}
