package com.lms.dto.response;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder public class EmiResponse{ private Long id; private Integer emiNumber; private java.math.BigDecimal amount; private String status; }
