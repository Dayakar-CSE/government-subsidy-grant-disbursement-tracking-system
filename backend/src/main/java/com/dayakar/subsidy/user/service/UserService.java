package com.dayakar.subsidy.user.service;

import com.dayakar.subsidy.user.dto.UserRegistrationRequest;
import com.dayakar.subsidy.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest request);

    UserResponse getUserById(Long userId);

    List<UserResponse> getAllUsers();

    void deleteUser(Long userId);

}