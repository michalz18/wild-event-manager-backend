package com.wildevent.WildEventMenager.event.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventTitleDTO(UUID eventId, UUID locationId, String title, LocalDateTime startsAt, String location) {
}
