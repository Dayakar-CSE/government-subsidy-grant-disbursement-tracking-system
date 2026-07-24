package com.dayakar.subsidy.application.service.impl;

import com.dayakar.subsidy.application.dto.ApplicationUpdateRequest;
import com.dayakar.subsidy.application.repository.ApplicationRepository;
import com.dayakar.subsidy.application.service.ApplicationService;
import com.dayakar.subsidy.beneficiary.repository.BeneficiaryRepository;
import com.dayakar.subsidy.scheme.repository.SchemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import com.dayakar.subsidy.application.dto.ApplicationRequest;
import com.dayakar.subsidy.application.dto.ApplicationResponse;
import com.dayakar.subsidy.application.entity.Application;
import com.dayakar.subsidy.beneficiary.entity.Beneficiary;
import com.dayakar.subsidy.common.enums.ApplicationStatus;
import com.dayakar.subsidy.exception.DuplicateResourceException;
import com.dayakar.subsidy.exception.ResourceNotFoundException;
import com.dayakar.subsidy.scheme.entity.Scheme;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final BeneficiaryRepository beneficiaryRepository;

    private final SchemeRepository schemeRepository;

    @Override
    public ApplicationResponse createApplication(ApplicationRequest request) {

        // Validate Beneficiary
        Beneficiary beneficiary = beneficiaryRepository.findById(request.getBeneficiaryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Beneficiary not found with ID: "
                                + request.getBeneficiaryId()));

        // Validate Scheme
        Scheme scheme = schemeRepository.findById(request.getSchemeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Scheme not found with ID: "
                                + request.getSchemeId()));

        // Check Duplicate Application
        if (applicationRepository.existsByBeneficiaryBeneficiaryIdAndSchemeSchemeId(
                beneficiary.getBeneficiaryId(),
                scheme.getSchemeId())) {

            throw new DuplicateResourceException(
                    "Beneficiary has already applied for this scheme.");
        }

        // Create Application
        Application application = Application.builder()
                .beneficiary(beneficiary)
                .scheme(scheme)
                .applicationDate(LocalDate.now())
                .status(ApplicationStatus.SUBMITTED)
                .remarks(request.getRemarks())
                .build();

        // First Save (Generate Database ID)
        application = applicationRepository.save(application);

        // Generate Business Application Number
        String applicationNumber = String.format(
                "APP-%d-%06d",
                LocalDate.now().getYear(),
                application.getApplicationId());

        application.setApplicationNumber(applicationNumber);

        // Second Save
        application = applicationRepository.save(application);

        // Return DTO
        return mapToResponse(application);
    }

    private ApplicationResponse mapToResponse(Application application) {

        return ApplicationResponse.builder()
                .applicationId(application.getApplicationId())
                .applicationNumber(application.getApplicationNumber())

                .beneficiaryId(application.getBeneficiary().getBeneficiaryId())
                .beneficiaryName(
                        application.getBeneficiary().getFirstName()
                                + " "
                                + application.getBeneficiary().getLastName())

                .schemeId(application.getScheme().getSchemeId())
                .schemeCode(application.getScheme().getSchemeCode())
                .schemeName(application.getScheme().getSchemeName())

                .applicationDate(application.getApplicationDate())
                .status(application.getStatus())
                .remarks(application.getRemarks())
                .build();
    }

    @Override
    public ApplicationResponse getApplicationById(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with ID: " + applicationId));

        return mapToResponse(application);
    }

    @Override
    public ApplicationResponse getApplicationByApplicationNumber(String applicationNumber) {

        Application application = applicationRepository
                .findByApplicationNumber(applicationNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with Number: " + applicationNumber));

        return mapToResponse(application);
    }

    @Override
    public List<ApplicationResponse> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsByBeneficiary(Long beneficiaryId) {

        return applicationRepository.findByBeneficiaryBeneficiaryId(beneficiaryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsByScheme(Long schemeId) {

        return applicationRepository.findBySchemeSchemeId(schemeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsByStatus(ApplicationStatus status) {

        return applicationRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ApplicationResponse updateApplication(Long applicationId,
                                                 ApplicationUpdateRequest request) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with ID: " + applicationId));

        application.setRemarks(request.getRemarks());

        application = applicationRepository.save(application);

        return mapToResponse(application);
    }

    @Override
    public void deleteApplication(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with ID: " + applicationId));

        applicationRepository.delete(application);
    }

}