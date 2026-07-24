package com.dayakar.subsidy.application.repository;

import com.dayakar.subsidy.application.entity.Application;
import com.dayakar.subsidy.common.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByApplicationNumber(String applicationNumber);

    boolean existsByApplicationNumber(String applicationNumber);

    boolean existsByBeneficiaryBeneficiaryIdAndSchemeSchemeId(
            Long beneficiaryId,
            Long schemeId
    );

    List<Application> findByBeneficiaryBeneficiaryId(Long beneficiaryId);

    List<Application> findBySchemeSchemeId(Long schemeId);

    List<Application> findByStatus(ApplicationStatus status);

}