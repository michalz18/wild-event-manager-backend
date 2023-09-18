package com.wildevent.WildEventMenager.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    @Email(message = "Email format should be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @Size(min = 8, max = 40, message = "Password must be between 8 and 40 characters")
    private String password;
}
