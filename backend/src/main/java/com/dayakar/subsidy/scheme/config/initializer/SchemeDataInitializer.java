package com.dayakar.subsidy.scheme.config.initializer;

import com.dayakar.subsidy.scheme.entity.Scheme;
import com.dayakar.subsidy.scheme.repository.SchemeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class SchemeDataInitializer implements CommandLineRunner {

    private final SchemeRepository schemeRepository;

    public SchemeDataInitializer(SchemeRepository schemeRepository) {
        this.schemeRepository = schemeRepository;
    }

    @Override
    public void run(String... args) {

        createSchemeIfNotExists(
                "PMAY-2026",
                "Pradhan Mantri Awas Yojana",
                "Affordable housing scheme for eligible families.",
                new BigDecimal("250000"),
                new BigDecimal("300000"),
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2027, 12, 31)
        );

        createSchemeIfNotExists(
                "LIG-2026",
                "Lower Income Group Housing Scheme",
                "Financial assistance for LIG housing beneficiaries.",
                new BigDecimal("500000"),
                new BigDecimal("400000"),
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2027, 12, 31)
        );

        createSchemeIfNotExists(
                "RYTHU-2026",
                "Farmer Support Scheme",
                "Financial support for eligible farmers.",
                new BigDecimal("15000"),
                new BigDecimal("500000"),
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 12, 31)
        );
    }

    private void createSchemeIfNotExists(
            String schemeCode,
            String schemeName,
            String description,
            BigDecimal maximumBenefitAmount,
            BigDecimal incomeLimit,
            LocalDate startDate,
            LocalDate endDate) {

        if (schemeRepository.existsBySchemeCode(schemeCode)) {
            return;
        }

        Scheme scheme = Scheme.builder()
                .schemeCode(schemeCode)
                .schemeName(schemeName)
                .description(description)
                .maximumBenefitAmount(maximumBenefitAmount)
                .incomeLimit(incomeLimit)
                .startDate(startDate)
                .endDate(endDate)
                .active(true)
                .build();

        schemeRepository.save(scheme);
    }
}