package com.dayakar.subsidy.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationRequest {

    @NotNull(message = "Beneficiary ID is required")
    private Long beneficiaryId;

    @NotNull(message = "Scheme ID is required")
    private Long schemeId;

    @Size(max = 500)
    private String remarks;
}