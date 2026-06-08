package com.lms.security;

import com.lms.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	// ✅ Manual constructor (IMPORTANT FIX)
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).map(user -> new CustomUserDetails(user))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}