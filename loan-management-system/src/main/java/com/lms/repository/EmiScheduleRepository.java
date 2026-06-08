package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.EmiSchedule;

public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {

	List<EmiSchedule> findByLoanAccountId(Long loanAccountId);
}