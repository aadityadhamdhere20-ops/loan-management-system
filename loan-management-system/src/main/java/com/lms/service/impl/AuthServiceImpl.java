package com.lms.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dto.request.LoginRequest;
import com.lms.dto.request.RegisterRequest;
import com.lms.dto.response.ApiResponse;
import com.lms.dto.response.LoginResponse;
import com.lms.entity.User;
import com.lms.enums.Role;
import com.lms.repository.UserRepository;
import com.lms.security.JwtUtil;
import com.lms.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder encoder;
	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;

	// ✅ Constructor Injection (no Lombok)
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authManager,
			JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	// ✅ REGISTER
	@Override
	public ApiResponse register(RegisterRequest r) {

		if (userRepository.existsByEmail(r.getEmail())) {
			return new ApiResponse(false, "Email already exists", null);
		}

		User u = new User();
		u.setFullName(r.getFullName());
		u.setEmail(r.getEmail());
		u.setMobileNumber(r.getMobileNumber());
		u.setPassword(encoder.encode(r.getPassword()));
		u.setAge(r.getAge());
		u.setMonthlyIncome(r.getMonthlyIncome());
		u.setRole(Role.USER);
		u.setDeleted(false);

		userRepository.save(u);

		return new ApiResponse(true, "User registered successfully", u.getId());
	}

	// ✅ LOGIN
	@Override
	public LoginResponse login(LoginRequest r) {

		authManager.authenticate(new UsernamePasswordAuthenticationToken(r.getUsername(), r.getPassword()));

		User u = userRepository.findByEmail(r.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

		u.setLastLoginAt(LocalDateTime.now());
		userRepository.save(u);

		String token = jwtUtil.generateToken(u.getEmail(), u.getRole().name());

		return new LoginResponse(token, u.getRole().name(), u.getId());
	}
}