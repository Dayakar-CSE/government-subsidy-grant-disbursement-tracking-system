package com.dayakar.subsidy.beneficiary.service;

import com.dayakar.subsidy.beneficiary.dto.BeneficiaryRegistrationRequest;
import com.dayakar.subsidy.beneficiary.dto.BeneficiaryResponse;
import com.dayakar.subsidy.beneficiary.dto.BeneficiaryUpdateRequest;

import java.util.List;

public interface BeneficiaryService {

    BeneficiaryResponse registerBeneficiary(BeneficiaryRegistrationRequest request);

    BeneficiaryResponse getBeneficiaryById(Long beneficiaryId);

    List<BeneficiaryResponse> getAllBeneficiaries();

    BeneficiaryResponse updateBeneficiary(
            Long beneficiaryId,
            BeneficiaryUpdateRequest request);

    void deleteBeneficiary(Long beneficiaryId);

}