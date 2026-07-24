package com.dayakar.subsidy.beneficiary.controller;

import com.dayakar.subsidy.beneficiary.dto.BeneficiaryRegistrationRequest;
import com.dayakar.subsidy.beneficiary.dto.BeneficiaryResponse;
import com.dayakar.subsidy.beneficiary.dto.BeneficiaryUpdateRequest;
import com.dayakar.subsidy.beneficiary.service.BeneficiaryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficiaries")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeneficiaryResponse registerBeneficiary(
            @Valid @RequestBody BeneficiaryRegistrationRequest request) {

        return beneficiaryService.registerBeneficiary(request);
    }

    @GetMapping
    public List<BeneficiaryResponse> getAllBeneficiaries() {

        return beneficiaryService.getAllBeneficiaries();

    }

    @GetMapping("/{id}")
    public BeneficiaryResponse getBeneficiaryById(
            @PathVariable Long id) {

        return beneficiaryService.getBeneficiaryById(id);

    }

    @PutMapping("/{id}")
    public BeneficiaryResponse updateBeneficiary(
            @PathVariable Long id,
            @Valid @RequestBody BeneficiaryUpdateRequest request) {

        return beneficiaryService.updateBeneficiary(id, request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeneficiary(
            @PathVariable Long id) {

        beneficiaryService.deleteBeneficiary(id);

    }

}