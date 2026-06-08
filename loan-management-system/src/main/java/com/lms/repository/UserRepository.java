package com.lms.repository;

import com.lms.entity.User;
import java.util.*;
import com.lms.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); Optional<User> findByMobileNumber(String mobileNumber); boolean existsByEmail(String email); boolean existsByMobileNumber(String mobileNumber); long countByRole(Role role);
}
