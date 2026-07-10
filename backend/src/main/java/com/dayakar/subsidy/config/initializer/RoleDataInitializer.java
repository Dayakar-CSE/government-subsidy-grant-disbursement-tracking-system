package com.dayakar.subsidy.config.initializer;

import com.dayakar.subsidy.common.enums.RoleType;
import com.dayakar.subsidy.role.entity.Role;
import com.dayakar.subsidy.role.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleDataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleDataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        createRoleIfNotExists(
                RoleType.ADMIN,
                "System Administrator"
        );

        createRoleIfNotExists(
                RoleType.OFFICER,
                "Government Officer"
        );

        createRoleIfNotExists(
                RoleType.BENEFICIARY,
                "Citizen Beneficiary"
        );

    }

    private void createRoleIfNotExists(
            RoleType roleType,
            String description) {

        if (!roleRepository.existsByRoleName(roleType)) {

            roleRepository.save(

                    Role.builder()
                            .roleName(roleType)
                            .description(description)
                            .build()

            );

        }

    }

}