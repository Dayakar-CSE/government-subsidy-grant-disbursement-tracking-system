package com.dayakar.subsidy.scheme.service.impl;

import com.dayakar.subsidy.exception.BadRequestException;
import com.dayakar.subsidy.exception.DuplicateResourceException;
import com.dayakar.subsidy.exception.ResourceNotFoundException;
import com.dayakar.subsidy.scheme.dto.SchemeRequest;
import com.dayakar.subsidy.scheme.dto.SchemeResponse;
import com.dayakar.subsidy.scheme.dto.SchemeUpdateRequest;
import com.dayakar.subsidy.scheme.entity.Scheme;
import com.dayakar.subsidy.scheme.repository.SchemeRepository;
import com.dayakar.subsidy.scheme.service.SchemeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemeServiceImpl implements SchemeService {

    private final SchemeRepository schemeRepository;

    public SchemeServiceImpl(SchemeRepository schemeRepository) {
        this.schemeRepository = schemeRepository;
    }

    @Override
    public SchemeResponse createScheme(SchemeRequest request) {

        if (schemeRepository.existsBySchemeCode(request.getSchemeCode())) {
            throw new DuplicateResourceException("Scheme code already exists.");
        }

        validateDates(request.getStartDate(), request.getEndDate());

        Scheme scheme = mapToEntity(request);

        Scheme savedScheme = schemeRepository.save(scheme);

        return mapToResponse(savedScheme);
    }

    @Override
    public SchemeResponse getSchemeById(Long schemeId) {

        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Scheme not found."));

        return mapToResponse(scheme);
    }

    @Override
    public List<SchemeResponse> getAllSchemes() {

        return schemeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<SchemeResponse> getActiveSchemes() {

        return schemeRepository.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SchemeResponse updateScheme(Long schemeId,
                                       SchemeUpdateRequest request) {

        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Scheme not found."));

        validateDates(request.getStartDate(), request.getEndDate());

        scheme.setSchemeName(request.getSchemeName());
        scheme.setDescription(request.getDescription());
        scheme.setMaximumBenefitAmount(request.getMaximumBenefitAmount());
        scheme.setIncomeLimit(request.getIncomeLimit());
        scheme.setStartDate(request.getStartDate());
        scheme.setEndDate(request.getEndDate());
        scheme.setActive(request.getActive());

        Scheme updatedScheme = schemeRepository.save(scheme);

        return mapToResponse(updatedScheme);
    }

    @Override
    public void deleteScheme(Long schemeId) {

        if (!schemeRepository.existsById(schemeId)) {
            throw new ResourceNotFoundException("Scheme not found.");
        }

        schemeRepository.deleteById(schemeId);
    }

    private void validateDates(java.time.LocalDate startDate,
                               java.time.LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new BadRequestException(
                    "Start date cannot be after end date.");
        }
    }

    private Scheme mapToEntity(SchemeRequest request) {

        return Scheme.builder()
                .schemeCode(request.getSchemeCode())
                .schemeName(request.getSchemeName())
                .description(request.getDescription())
                .maximumBenefitAmount(request.getMaximumBenefitAmount())
                .incomeLimit(request.getIncomeLimit())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .active(request.getActive())
                .build();
    }

    private SchemeResponse mapToResponse(Scheme scheme) {

        return SchemeResponse.builder()
                .schemeId(scheme.getSchemeId())
                .schemeCode(scheme.getSchemeCode())
                .schemeName(scheme.getSchemeName())
                .description(scheme.getDescription())
                .maximumBenefitAmount(scheme.getMaximumBenefitAmount())
                .incomeLimit(scheme.getIncomeLimit())
                .startDate(scheme.getStartDate())
                .endDate(scheme.getEndDate())
                .active(scheme.getActive())
                .build();
    }
}