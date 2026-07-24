package com.dayakar.subsidy.application.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUpdateRequest {

    @Size(max = 500)
    private String remarks;
}