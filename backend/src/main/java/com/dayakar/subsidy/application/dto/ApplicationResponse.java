package com.dayakar.subsidy.application.dto;

import com.dayakar.subsidy.common.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {

    private Long applicationId;

    private String applicationNumber;

    private Long beneficiaryId;

    private String beneficiaryName;

    private Long schemeId;

    private String schemeCode;

    private String schemeName;

    private LocalDate applicationDate;

    private ApplicationStatus status;

    private String remarks;
}