package com.dayakar.subsidy.beneficiary.dto;

import com.dayakar.subsidy.common.enums.CategoryType;
import com.dayakar.subsidy.common.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficiaryRegistrationRequest {

    @Pattern(regexp = "^\\d{12}$",
            message = "Aadhaar number must contain exactly 12 digits")
    private String aadhaarNumber;

    @NotBlank(message = "First name is required")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100)
    private String lastName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

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

    @NotNull(message = "User ID is required")
    private Long userId;

}