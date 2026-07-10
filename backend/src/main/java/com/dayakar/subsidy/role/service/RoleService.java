package com.dayakar.subsidy.role.service;

import com.dayakar.subsidy.common.enums.RoleType;
import com.dayakar.subsidy.role.dto.RoleRequest;
import com.dayakar.subsidy.role.dto.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long roleId);

    RoleResponse getRoleByRoleName(RoleType roleType);

    void deleteRole(Long roleId);

}