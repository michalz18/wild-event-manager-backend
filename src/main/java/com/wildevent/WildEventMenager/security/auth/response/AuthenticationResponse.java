package com.wildevent.WildEventMenager.security.auth.response;

import com.wildevent.WildEventMenager.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String id;
    private String name;
    private String email;
    private Set<Role> roles;
}
