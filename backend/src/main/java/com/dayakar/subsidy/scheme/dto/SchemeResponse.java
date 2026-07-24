package com.dayakar.subsidy.scheme.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchemeResponse {

    private Long schemeId;

    private String schemeCode;

    private String schemeName;

    private String description;

    private BigDecimal maximumBenefitAmount;

    private BigDecimal incomeLimit;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean active;
}