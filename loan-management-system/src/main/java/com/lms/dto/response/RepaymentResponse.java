package com.lms.dto.response;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder public class RepaymentResponse{ private String transactionId; private String message; private java.math.BigDecimal paidAmount; }
