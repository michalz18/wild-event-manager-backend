package com.wildevent.WildEventMenager.event.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;
public record EventTitleDTO(UUID eventId, String title, LocalDateTime startsAt, String location) {
}
