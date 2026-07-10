package com.dayakar.subsidy.role.dto;

import com.dayakar.subsidy.common.enums.RoleType;
import lombok.*;

/**
 * Response DTO returned to the client.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {

    private Long roleId;

    private RoleType roleName;

    private String description;

}