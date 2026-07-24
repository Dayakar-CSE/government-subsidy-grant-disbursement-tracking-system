package com.dayakar.subsidy.application.service;

import com.dayakar.subsidy.application.dto.ApplicationRequest;
import com.dayakar.subsidy.application.dto.ApplicationResponse;
import com.dayakar.subsidy.application.dto.ApplicationUpdateRequest;
import com.dayakar.subsidy.common.enums.ApplicationStatus;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse createApplication(ApplicationRequest request);

    ApplicationResponse getApplicationById(Long applicationId);

    ApplicationResponse getApplicationByApplicationNumber(String applicationNumber);

    List<ApplicationResponse> getAllApplications();

    List<ApplicationResponse> getApplicationsByBeneficiary(Long beneficiaryId);

    List<ApplicationResponse> getApplicationsByScheme(Long schemeId);

    List<ApplicationResponse> getApplicationsByStatus(ApplicationStatus status);

    ApplicationResponse updateApplication(Long applicationId,
                                          ApplicationUpdateRequest request);

    void deleteApplication(Long applicationId);

}