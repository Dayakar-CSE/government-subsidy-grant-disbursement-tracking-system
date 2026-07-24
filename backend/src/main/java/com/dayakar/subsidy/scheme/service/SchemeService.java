package com.dayakar.subsidy.scheme.service;

import com.dayakar.subsidy.scheme.dto.SchemeRequest;
import com.dayakar.subsidy.scheme.dto.SchemeResponse;
import com.dayakar.subsidy.scheme.dto.SchemeUpdateRequest;

import java.util.List;

public interface SchemeService {

    SchemeResponse createScheme(SchemeRequest request);

    SchemeResponse getSchemeById(Long schemeId);

    List<SchemeResponse> getAllSchemes();

    List<SchemeResponse> getActiveSchemes();

    SchemeResponse updateScheme(Long schemeId,
                                SchemeUpdateRequest request);

    void deleteScheme(Long schemeId);

}