package com.wildevent.WildEventMenager.event.model;

import java.time.LocalDateTime;

public record EventDTO(String title, String description, LocalDateTime startsAt, LocalDateTime endsAt, String location) {
}
