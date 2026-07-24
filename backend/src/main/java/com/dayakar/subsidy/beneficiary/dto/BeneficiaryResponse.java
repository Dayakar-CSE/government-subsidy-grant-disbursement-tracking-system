package com.dayakar.subsidy.beneficiary.dto;

import com.dayakar.subsidy.common.enums.CategoryType;
import com.dayakar.subsidy.common.enums.Gender;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficiaryResponse {

    private Long beneficiaryId;

    private String aadhaarNumber;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Gender gender;

    private CategoryType category;

    private BigDecimal annualIncome;

    private String occupation;

    private String addressLine;

    private Long userId;

    private String username;

}