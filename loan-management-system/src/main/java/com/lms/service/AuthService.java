package com.lms.service;

import com.lms.dto.request.LoginRequest;
import com.lms.dto.request.RegisterRequest;
import com.lms.dto.response.ApiResponse;
import com.lms.dto.response.LoginResponse;

public interface AuthService {

	ApiResponse register(RegisterRequest request);

	LoginResponse login(LoginRequest request);
}