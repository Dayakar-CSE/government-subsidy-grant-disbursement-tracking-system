package com.dayakar.subsidy.role.repository;

import com.dayakar.subsidy.common.enums.RoleType;
import com.dayakar.subsidy.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for Role entity.
 * Provides CRUD operations and custom queries.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its role type.
     *
     * @param roleType the role type
     * @return optional role
     */
    Optional<Role> findByRoleName(RoleType roleType);

    /**
     * Checks whether a role already exists.
     *
     * @param roleType the role type
     * @return true if role exists
     */
    boolean existsByRoleName(RoleType roleType);

}