package com.dayakar.subsidy.scheme.controller;

import com.dayakar.subsidy.scheme.dto.SchemeRequest;
import com.dayakar.subsidy.scheme.dto.SchemeResponse;
import com.dayakar.subsidy.scheme.dto.SchemeUpdateRequest;
import com.dayakar.subsidy.scheme.service.SchemeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schemes")
public class SchemeController {

    private final SchemeService schemeService;

    public SchemeController(SchemeService schemeService) {
        this.schemeService = schemeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchemeResponse createScheme(
            @Valid @RequestBody SchemeRequest request) {

        return schemeService.createScheme(request);
    }

    @GetMapping
    public List<SchemeResponse> getAllSchemes() {

        return schemeService.getAllSchemes();
    }

    @GetMapping("/{id}")
    public SchemeResponse getSchemeById(
            @PathVariable Long id) {

        return schemeService.getSchemeById(id);
    }

    @GetMapping("/active")
    public List<SchemeResponse> getActiveSchemes() {

        return schemeService.getActiveSchemes();
    }

    @PutMapping("/{id}")
    public SchemeResponse updateScheme(
            @PathVariable Long id,
            @Valid @RequestBody SchemeUpdateRequest request) {

        return schemeService.updateScheme(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheme(
            @PathVariable Long id) {

        schemeService.deleteScheme(id);
    }

}