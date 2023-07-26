package com.wildevent.WildEventMenager.event.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class EventTitleDTO {
    private String title;
    private LocalDateTime startsAt;
    private String location;

    public EventTitleDTO(String title, LocalDateTime startsAt, String location) {
        this.title = title;
        this.startsAt = startsAt;
        this.location = location;
    }
}
