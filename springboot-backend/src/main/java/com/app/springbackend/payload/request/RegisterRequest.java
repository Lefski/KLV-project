package com.app.springbackend.payload.request;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username, userEmail, password;

    private Set<String> role;
}
