package com.dayakar.subsidy.beneficiary.service.impl;

import com.dayakar.subsidy.beneficiary.dto.BeneficiaryRegistrationRequest;
import com.dayakar.subsidy.beneficiary.dto.BeneficiaryResponse;
import com.dayakar.subsidy.beneficiary.dto.BeneficiaryUpdateRequest;
import com.dayakar.subsidy.beneficiary.entity.Beneficiary;
import com.dayakar.subsidy.beneficiary.repository.BeneficiaryRepository;
import com.dayakar.subsidy.beneficiary.service.BeneficiaryService;
import com.dayakar.subsidy.exception.DuplicateResourceException;
import com.dayakar.subsidy.exception.ResourceNotFoundException;
import com.dayakar.subsidy.user.entity.User;
import com.dayakar.subsidy.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final UserRepository userRepository;

    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository,
                                  UserRepository userRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BeneficiaryResponse registerBeneficiary(BeneficiaryRegistrationRequest request) {

        if (beneficiaryRepository.existsByAadhaarNumber(request.getAadhaarNumber())) {
            throw new DuplicateResourceException("Beneficiary with Aadhaar already exists.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        if (beneficiaryRepository.findByUserUserId(user.getUserId()).isPresent()) {
            throw new DuplicateResourceException("Beneficiary profile already exists for this user.");
        }

        Beneficiary beneficiary = mapToEntity(request, user);

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);

        return mapToResponse(savedBeneficiary);
    }

    @Override
    public BeneficiaryResponse getBeneficiaryById(Long beneficiaryId) {

        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Beneficiary not found."));

        return mapToResponse(beneficiary);
    }

    @Override
    public List<BeneficiaryResponse> getAllBeneficiaries() {

        return beneficiaryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BeneficiaryResponse updateBeneficiary(Long beneficiaryId,
                                                 BeneficiaryUpdateRequest request) {

        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Beneficiary not found."));

        beneficiary.setFirstName(request.getFirstName());
        beneficiary.setLastName(request.getLastName());
        beneficiary.setGender(request.getGender());
        beneficiary.setCategory(request.getCategory());
        beneficiary.setAnnualIncome(request.getAnnualIncome());
        beneficiary.setOccupation(request.getOccupation());
        beneficiary.setAddressLine(request.getAddressLine());

        Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);

        return mapToResponse(updatedBeneficiary);
    }

    @Override
    public void deleteBeneficiary(Long beneficiaryId) {

        if (!beneficiaryRepository.existsById(beneficiaryId)) {
            throw new ResourceNotFoundException("Beneficiary not found.");
        }

        beneficiaryRepository.deleteById(beneficiaryId);
    }

    private Beneficiary mapToEntity(BeneficiaryRegistrationRequest request,
                                    User user) {

        return Beneficiary.builder()
                .aadhaarNumber(request.getAadhaarNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .category(request.getCategory())
                .annualIncome(request.getAnnualIncome())
                .occupation(request.getOccupation())
                .addressLine(request.getAddressLine())
                .user(user)
                .build();
    }

    private BeneficiaryResponse mapToResponse(Beneficiary beneficiary) {

        return BeneficiaryResponse.builder()
                .beneficiaryId(beneficiary.getBeneficiaryId())
                .aadhaarNumber(beneficiary.getAadhaarNumber())
                .firstName(beneficiary.getFirstName())
                .lastName(beneficiary.getLastName())
                .dateOfBirth(beneficiary.getDateOfBirth())
                .gender(beneficiary.getGender())
                .category(beneficiary.getCategory())
                .annualIncome(beneficiary.getAnnualIncome())
                .occupation(beneficiary.getOccupation())
                .addressLine(beneficiary.getAddressLine())
                .userId(beneficiary.getUser().getUserId())
                .username(beneficiary.getUser().getUsername())
                .build();
    }

}