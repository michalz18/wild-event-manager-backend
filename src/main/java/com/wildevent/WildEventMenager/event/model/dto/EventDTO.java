package com.wildevent.WildEventMenager.event.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDTO(UUID id, String title, String description, LocalDateTime startsAt, LocalDateTime endsAt,
                       String location) {
}
