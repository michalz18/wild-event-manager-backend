package com.wildevent.WildEventMenager.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
public class DateRange {
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
}
