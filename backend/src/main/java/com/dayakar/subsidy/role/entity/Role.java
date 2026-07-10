package com.dayakar.subsidy.role.entity;

import com.dayakar.subsidy.common.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a user role in the system.
 * Examples:
 * ADMIN
 * OFFICER
 * BENEFICIARY
 */
@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, unique = true, length = 50)
    private RoleType roleName;

    @Column(name = "description", length = 255)
    private String description;

}