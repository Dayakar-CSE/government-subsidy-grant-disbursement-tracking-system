package com.dayakar.subsidy.application.controller;

import com.dayakar.subsidy.application.dto.ApplicationRequest;
import com.dayakar.subsidy.application.dto.ApplicationResponse;
import com.dayakar.subsidy.application.dto.ApplicationUpdateRequest;
import com.dayakar.subsidy.application.service.ApplicationService;
import com.dayakar.subsidy.common.enums.ApplicationStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @Valid @RequestBody ApplicationRequest request) {

        ApplicationResponse response = applicationService.createApplication(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                applicationService.getApplicationById(id));
    }

    @GetMapping("/number/{applicationNumber}")
    public ResponseEntity<ApplicationResponse> getApplicationByApplicationNumber(
            @PathVariable String applicationNumber) {

        return ResponseEntity.ok(
                applicationService.getApplicationByApplicationNumber(applicationNumber));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {

        return ResponseEntity.ok(
                applicationService.getAllApplications());
    }

    @GetMapping("/beneficiary/{beneficiaryId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByBeneficiary(
            @PathVariable Long beneficiaryId) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByBeneficiary(beneficiaryId));
    }

    @GetMapping("/scheme/{schemeId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByScheme(
            @PathVariable Long schemeId) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByScheme(schemeId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByStatus(
            @PathVariable ApplicationStatus status) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationUpdateRequest request) {

        return ResponseEntity.ok(
                applicationService.updateApplication(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable Long id) {

        applicationService.deleteApplication(id);

        return ResponseEntity.noContent().build();
    }
}