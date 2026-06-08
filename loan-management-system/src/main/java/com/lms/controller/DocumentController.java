package com.lms.controller;

import com.lms.dto.response.ApiResponse;
import com.lms.entity.DocumentUpload;
import com.lms.entity.LoanApplication;
import com.lms.enums.DocumentStatus;
import com.lms.enums.DocumentType;
import com.lms.repository.DocumentUploadRepository;
import com.lms.repository.LoanApplicationRepository;
import com.lms.util.FileUploadUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

	private final LoanApplicationRepository loanRepo;
	private final DocumentUploadRepository docRepo;

	@Value("${app.upload.dir}")
	private String uploadDir;

	public DocumentController(LoanApplicationRepository loanRepo, DocumentUploadRepository docRepo) {
		this.loanRepo = loanRepo;
		this.docRepo = docRepo;
	}

	@PostMapping("/upload")
	public Object upload(@RequestParam Long applicationId, @RequestParam DocumentType type,
			@RequestParam MultipartFile file) throws Exception {

		LoanApplication app = loanRepo.findById(applicationId)
				.orElseThrow(() -> new RuntimeException("Loan application not found"));

		String path = FileUploadUtil.save(uploadDir, file);

		DocumentUpload d = new DocumentUpload();
		d.setApplication(app);
		d.setDocumentType(type);
		d.setDocumentUrl(path);
		d.setDocumentStatus(DocumentStatus.UPLOADED);

		docRepo.save(d);

		return new ApiResponse(true, "Document uploaded", d.getId());
	}

	@PostMapping("/approve/{id}")
	public Object approve(@PathVariable Long id) {

		DocumentUpload d = docRepo.findById(id).orElseThrow(() -> new RuntimeException("Document not found"));

		d.setDocumentStatus(DocumentStatus.VERIFIED);
		docRepo.save(d);

		return new ApiResponse(true, "Document verified", null);
	}

	@PostMapping("/reject/{id}")
	public Object reject(@PathVariable Long id, @RequestParam String remarks) {

		DocumentUpload d = docRepo.findById(id).orElseThrow(() -> new RuntimeException("Document not found"));

		d.setDocumentStatus(DocumentStatus.REJECTED);
		d.setRemarks(remarks);
		docRepo.save(d);

		return new ApiResponse(true, "Document rejected", null);
	}

	@PostMapping("/return/{id}")
	public Object ret(@PathVariable Long id, @RequestParam String remarks) {

		DocumentUpload d = docRepo.findById(id).orElseThrow(() -> new RuntimeException("Document not found"));

		d.setDocumentStatus(DocumentStatus.RETURNED);
		d.setRemarks(remarks);
		docRepo.save(d);

		return new ApiResponse(true, "Document returned", null);
	}
}