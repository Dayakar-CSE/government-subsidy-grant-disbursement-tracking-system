package com.dayakar.subsidy.role.dto;

import com.dayakar.subsidy.common.enums.RoleType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Request DTO used to create or update a Role.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {

    @NotNull(message = "Role name is required")
    private RoleType roleName;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

}