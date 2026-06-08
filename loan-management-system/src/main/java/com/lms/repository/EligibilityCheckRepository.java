package com.lms.repository;

import com.lms.entity.EligibilityCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EligibilityCheckRepository extends JpaRepository<EligibilityCheck, Long> {
    
}
