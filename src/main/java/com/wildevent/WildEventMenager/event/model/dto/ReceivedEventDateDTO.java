package com.wildevent.WildEventMenager.event.model.dto;

import com.wildevent.WildEventMenager.event.model.DateRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceivedEventDateDTO {
    @org.hibernate.validator.constraints.UUID
    private String id;
    private DateRange dateRange;
}
