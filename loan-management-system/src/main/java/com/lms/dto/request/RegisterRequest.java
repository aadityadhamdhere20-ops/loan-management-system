package com.lms.dto.request;

import java.math.BigDecimal;

public class RegisterRequest {

	private String fullName;
	private String email;
	private String mobileNumber;
	private String password;
	private Integer age;
	private BigDecimal monthlyIncome;

	public RegisterRequest() {
	}

	public RegisterRequest(String fullName, String email, String mobileNumber, String password, Integer age,
			BigDecimal monthlyIncome) {
		this.fullName = fullName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.age = age;
		this.monthlyIncome = monthlyIncome;
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
}