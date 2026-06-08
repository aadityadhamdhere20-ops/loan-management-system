package com.lms.repository;

import com.lms.entity.LoanApplication;
import java.util.*;import com.lms.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByApplicationStatus(LoanStatus status); List<LoanApplication> findByUserId(Long userId);
}
