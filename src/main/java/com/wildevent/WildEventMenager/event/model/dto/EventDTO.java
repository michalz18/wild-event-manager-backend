package com.wildevent.WildEventMenager.event.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EventDTO(UUID id, String title, String description, LocalDateTime startsAt, LocalDateTime endsAt,
                       String location, List<String> organizers,boolean openToPublic) {
    public EventDTO(String title, String description, LocalDateTime startsAt, LocalDateTime endsAt, String location, List<String> organizers,boolean openToPublic) {
        this(null, title, description, startsAt, endsAt, location,organizers,openToPublic);
    }
}
