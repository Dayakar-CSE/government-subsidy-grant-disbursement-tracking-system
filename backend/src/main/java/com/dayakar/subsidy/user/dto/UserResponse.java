package com.dayakar.subsidy.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long userId;

    private String username;

    private String email;

    private String phone;

    private Boolean enabled;

    private String role;

}