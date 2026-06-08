package com.lms.entity;

import java.time.LocalDateTime;

import com.lms.enums.DocumentStatus;
import com.lms.enums.DocumentType;

import jakarta.persistence.*;

@Entity
public class DocumentUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private LoanApplication application;

	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	@Column(columnDefinition = "TEXT")
	private String documentUrl;

	@Enumerated(EnumType.STRING)
	private DocumentStatus documentStatus;

	@Column(columnDefinition = "TEXT")
	private String remarks;

	private LocalDateTime uploadedAt;
	private LocalDateTime verifiedAt;

	@ManyToOne
	private User verifiedBy;

	// ✅ PrePersist
	@PrePersist
	public void pre() {
		uploadedAt = LocalDateTime.now();
		if (documentStatus == null) {
			documentStatus = DocumentStatus.UPLOADED;
		}
	}

	// ✅ Constructors
	public DocumentUpload() {
	}

	public DocumentUpload(Long id, LoanApplication application, DocumentType documentType, String documentUrl,
			DocumentStatus documentStatus, String remarks, LocalDateTime uploadedAt, LocalDateTime verifiedAt,
			User verifiedBy) {
		this.id = id;
		this.application = application;
		this.documentType = documentType;
		this.documentUrl = documentUrl;
		this.documentStatus = documentStatus;
		this.remarks = remarks;
		this.uploadedAt = uploadedAt;
		this.verifiedAt = verifiedAt;
		this.verifiedBy = verifiedBy;
	}

	// ✅ Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplication getApplication() {
		return application;
	}

	public void setApplication(LoanApplication application) {
		this.application = application;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public DocumentStatus getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(DocumentStatus documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public LocalDateTime getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(LocalDateTime verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public User getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(User verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
}