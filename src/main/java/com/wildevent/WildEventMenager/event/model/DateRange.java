package com.wildevent.WildEventMenager.event.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateRange {
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
}
