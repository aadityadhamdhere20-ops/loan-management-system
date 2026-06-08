package com.lms.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class EligibilityCheck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private LoanApplication application;

	private boolean ageEligible;
	private boolean cibilEligible;
	private boolean foirEligible;
	private boolean alreadyLoanActive;
	private boolean defaulted;
	private boolean finalEligible;

	private Integer simulatedCibilScore;
	private LocalDateTime checkedAt;

	// ✅ Constructor
	public EligibilityCheck() {
	}

	// ✅ PrePersist
	@PrePersist
	public void pre() {
		checkedAt = LocalDateTime.now();
	}

	// ✅ Getters & Setters

	public Long getId() {
		return id;
	}

	public LoanApplication getApplication() {
		return application;
	}

	public void setApplication(LoanApplication application) {
		this.application = application;
	}

	public boolean isAgeEligible() {
		return ageEligible;
	}

	public void setAgeEligible(boolean ageEligible) {
		this.ageEligible = ageEligible;
	}

	public boolean isCibilEligible() {
		return cibilEligible;
	}

	public void setCibilEligible(boolean cibilEligible) {
		this.cibilEligible = cibilEligible;
	}

	public boolean isFoirEligible() {
		return foirEligible;
	}

	public void setFoirEligible(boolean foirEligible) {
		this.foirEligible = foirEligible;
	}

	public boolean isAlreadyLoanActive() {
		return alreadyLoanActive;
	}

	public void setAlreadyLoanActive(boolean alreadyLoanActive) {
		this.alreadyLoanActive = alreadyLoanActive;
	}

	public boolean isDefaulted() {
		return defaulted;
	}

	public void setDefaulted(boolean defaulted) {
		this.defaulted = defaulted;
	}

	public boolean isFinalEligible() {
		return finalEligible;
	}

	public void setFinalEligible(boolean finalEligible) {
		this.finalEligible = finalEligible;
	}

	public Integer getSimulatedCibilScore() {
		return simulatedCibilScore;
	}

	public void setSimulatedCibilScore(Integer simulatedCibilScore) {
		this.simulatedCibilScore = simulatedCibilScore;
	}

	public LocalDateTime getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(LocalDateTime checkedAt) {
		this.checkedAt = checkedAt;
	}
}