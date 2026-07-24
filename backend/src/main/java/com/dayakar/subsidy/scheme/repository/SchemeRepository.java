package com.dayakar.subsidy.scheme.repository;

import com.dayakar.subsidy.scheme.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {

    Optional<Scheme> findBySchemeCode(String schemeCode);

    boolean existsBySchemeCode(String schemeCode);

    List<Scheme> findByActiveTrue();

}