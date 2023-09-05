package com.wildevent.WildEventMenager.user.model;

import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^\\d{9}$", message = "Invalid phone number format!")
    private String phone;
    private List<String> locationIds;
    @NotEmpty(message = "At least one role must be assigned!")
    private Set<String> roleIds;
}
