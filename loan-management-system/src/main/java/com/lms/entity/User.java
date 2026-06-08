package com.lms.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lms.enums.Role;

import jakarta.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;
	private String email;
	private String mobileNumber;
	private String password;

	private Integer age;
	private BigDecimal monthlyIncome;

	private LocalDateTime lastLoginAt;

	@Enumerated(EnumType.STRING)
	private Role role;

	private boolean isDeleted;
	private boolean isDefaulted; // ✅ IMPORTANT

	public User() {
	}

	// ---------- Getters & Setters ----------

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public LocalDateTime getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(LocalDateTime lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// ✅ REQUIRED METHOD (fix your error)
	public boolean isDefaulted() {
		return isDefaulted;
	}

	public void setDefaulted(boolean isDefaulted) {
		this.isDefaulted = isDefaulted;
	}
}