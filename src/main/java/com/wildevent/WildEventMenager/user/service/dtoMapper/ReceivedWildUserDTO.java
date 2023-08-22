package com.wildevent.WildEventMenager.user.service.dtoMapper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ReceivedWildUserDTO {
    @NotNull
    @Size(min = 3, max = 40)
    private String name;
    @NotNull
    @Email
    private String email;
    @Pattern(regexp = "^\\d{9}$", message = "Invalid phone number format")
    private String phone;
    @NotNull
    private boolean active;
    private List<String> locationIds;
    private Set<String> roleIds;
}
