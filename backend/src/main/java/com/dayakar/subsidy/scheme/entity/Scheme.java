package com.dayakar.subsidy.scheme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "scheme",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_scheme_code", columnNames = "scheme_code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schemeId;

    @NotBlank(message = "Scheme code is required")
    @Column(name = "scheme_code", nullable = false, unique = true, length = 30)
    private String schemeCode;

    @NotBlank(message = "Scheme name is required")
    @Column(name = "scheme_name", nullable = false, length = 150)
    private String schemeName;

    @Column(length = 500)
    private String description;

    @NotNull(message = "Maximum benefit amount is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "maximum_benefit_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal maximumBenefitAmount;

    @NotNull(message = "Income limit is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "income_limit", nullable = false, precision = 12, scale = 2)
    private BigDecimal incomeLimit;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
}