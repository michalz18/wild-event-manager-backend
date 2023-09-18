package com.wildevent.WildEventMenager.security.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email format should be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Size(min = 8, max = 40, message = "Password must be between 8 and 40 characters")
    private String password;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    private List<String> locationIds;

    @NotNull
    private Set<String> roleIds;
}
