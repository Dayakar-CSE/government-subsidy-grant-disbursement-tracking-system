package com.dayakar.subsidy.scheme.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchemeRequest {

    @NotBlank(message = "Scheme code is required")
    @Size(max = 30)
    private String schemeCode;

    @NotBlank(message = "Scheme name is required")
    @Size(max = 150)
    private String schemeName;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Maximum benefit amount is required")
    @DecimalMin(value = "0.01", message = "Maximum benefit amount must be greater than zero")
    private BigDecimal maximumBenefitAmount;

    @NotNull(message = "Income limit is required")
    @DecimalMin(value = "0.01", message = "Income limit must be greater than zero")
    private BigDecimal incomeLimit;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Active status is required")
    private Boolean active;
}