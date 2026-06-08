package com.lms.dto.request;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor public class ForeclosureRequest{ private Long loanAccountId; private com.lms.enums.PaymentMode paymentMode; }
