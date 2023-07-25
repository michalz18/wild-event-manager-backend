package com.wildevent.WildEventMenager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class EventDTO {
    private String title;
    private LocalDateTime startsAt;
    private String location;

    public EventDTO(String title, LocalDateTime startsAt, String location) {
        this.title = title;
        this.startsAt = startsAt;
        this.location = location;
    }
}
