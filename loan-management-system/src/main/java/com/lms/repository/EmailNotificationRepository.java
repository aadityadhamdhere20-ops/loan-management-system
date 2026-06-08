package com.lms.repository;

import com.lms.entity.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
    
}
