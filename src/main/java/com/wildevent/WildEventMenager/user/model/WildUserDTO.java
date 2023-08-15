package com.wildevent.WildEventMenager.user.model;


import java.util.List;
import java.util.UUID;

public record WildUserDTO(UUID id, String name, String email, String phone, List<String> roles, List<String> locations) {
}
