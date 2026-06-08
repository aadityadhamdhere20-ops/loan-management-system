package com.lms.repository;

import com.lms.entity.SanctionLetter;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanctionLetterRepository extends JpaRepository<SanctionLetter, Long> {
    Optional<SanctionLetter> findByApplicationId(Long applicationId);
}
