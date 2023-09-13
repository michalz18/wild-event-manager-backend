package com.wildevent.WildEventMenager.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordByUserRequestDTO {

    @Email(message = "Email format should be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;
}
