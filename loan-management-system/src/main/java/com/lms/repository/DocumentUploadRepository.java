package com.lms.repository;

import com.lms.entity.DocumentUpload;
import java.util.*;import com.lms.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentUploadRepository extends JpaRepository<DocumentUpload, Long> {
    List<DocumentUpload> findByApplicationId(Long applicationId); List<DocumentUpload> findByDocumentStatus(DocumentStatus status);
}
