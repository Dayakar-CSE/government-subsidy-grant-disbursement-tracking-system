package com.dayakar.subsidy.user.service.impl;

import com.dayakar.subsidy.common.enums.RoleType;
import com.dayakar.subsidy.role.entity.Role;
import com.dayakar.subsidy.role.repository.RoleRepository;
import com.dayakar.subsidy.user.dto.UserRegistrationRequest;
import com.dayakar.subsidy.user.dto.UserResponse;
import com.dayakar.subsidy.user.entity.User;
import com.dayakar.subsidy.user.repository.UserRepository;
import com.dayakar.subsidy.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        if (request.getPhone() != null &&
                userRepository.existsByPhone(request.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists.");
        }

        Role beneficiaryRole = roleRepository.findByRoleName(RoleType.BENEFICIARY)
                .orElseThrow(() -> new IllegalArgumentException("Beneficiary role not found."));

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .enabled(true)
                .role(beneficiaryRole)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .userId(savedUser.getUserId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .enabled(savedUser.getEnabled())
                .role(savedUser.getRole().getRoleName().name())
                .build();
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found."));

        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .enabled(user.getEnabled())
                .role(user.getRole().getRoleName().name())
                .build();
    }

}