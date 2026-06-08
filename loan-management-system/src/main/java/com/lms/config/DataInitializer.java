package com.lms.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lms.entity.User;
import com.lms.enums.Role;
import com.lms.repository.UserRepository;

@Configuration
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Bean
    CommandLineRunner createDefaultAdmin() {

        return args -> {

            if (!userRepository.existsByEmail(adminEmail)) {

                User admin = new User();

                admin.setFullName("System Admin");
                admin.setEmail(adminEmail);
                admin.setMobileNumber("9999999999");
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setAge(30);
                admin.setMonthlyIncome(BigDecimal.ZERO);
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);

                System.out.println("Admin Created Successfully");
            }
        };
    }
}