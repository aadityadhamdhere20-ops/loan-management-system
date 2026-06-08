package com.lms.controller;

import com.lms.dto.request.LoginRequest;
import com.lms.dto.request.RegisterRequest;
import com.lms.service.impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthServiceImpl service;

	public AuthController(AuthServiceImpl service) {
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest r) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.register(r));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest r) {
		return ResponseEntity.ok(service.login(r));
	}
}