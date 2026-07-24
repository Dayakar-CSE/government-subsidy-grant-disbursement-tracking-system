package com.dayakar.subsidy.beneficiary.dto;

import com.dayakar.subsidy.common.enums.CategoryType;
import com.dayakar.subsidy.common.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficiaryUpdateRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100)
    private String lastName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Category is required")
    private CategoryType category;

    @NotNull(message = "Annual income is required")
    @DecimalMin(value = "0.0")
    private BigDecimal annualIncome;

    @Size(max = 150)
    private String occupation;

    @NotBlank(message = "Address is required")
    @Size(max = 255)
    private String addressLine;

}