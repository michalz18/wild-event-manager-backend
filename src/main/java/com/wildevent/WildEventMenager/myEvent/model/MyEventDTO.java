package com.wildevent.WildEventMenager.myEvent.model;

import java.util.UUID;

public record MyEventDTO(UUID id, String title, String description, String location, boolean openToPublic, String startsAt, String endsAt) {
}

