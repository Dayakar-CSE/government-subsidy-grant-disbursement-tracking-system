package com.dayakar.subsidy.role.service.impl;

import com.dayakar.subsidy.common.enums.RoleType;
import com.dayakar.subsidy.exception.DuplicateResourceException;
import com.dayakar.subsidy.exception.ResourceNotFoundException;
import com.dayakar.subsidy.role.dto.RoleRequest;
import com.dayakar.subsidy.role.dto.RoleResponse;
import com.dayakar.subsidy.role.entity.Role;
import com.dayakar.subsidy.role.repository.RoleRepository;
import com.dayakar.subsidy.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {

        if (roleRepository.existsByRoleName(request.getRoleName())) {
            throw new DuplicateResourceException(
                    "Role already exists: " + request.getRoleName());
        }

        Role role = Role.builder()
                .roleName(request.getRoleName())
                .description(request.getDescription())
                .build();

        Role savedRole = roleRepository.save(role);

        return RoleResponse.builder()
                .roleId(savedRole.getRoleId())
                .roleName(savedRole.getRoleName())
                .description(savedRole.getDescription())
                .build();
    }

    @Override
    public List<RoleResponse> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(role -> RoleResponse.builder()
                        .roleId(role.getRoleId())
                        .roleName(role.getRoleName())
                        .description(role.getDescription())
                        .build())
                .toList();
    }

    @Override
    public RoleResponse getRoleById(Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Role not found"));

        return RoleResponse.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    @Override
    public RoleResponse getRoleByRoleName(RoleType roleType) {

        Role role = roleRepository.findByRoleName(roleType)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        return RoleResponse.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    @Override
    public void deleteRole(Long roleId) {

        roleRepository.deleteById(roleId);

    }

}