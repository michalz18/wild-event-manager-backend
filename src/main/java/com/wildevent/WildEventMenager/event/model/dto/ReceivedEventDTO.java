package com.wildevent.WildEventMenager.event.model.dto;

import com.wildevent.WildEventMenager.event.model.DateRange;
import com.wildevent.WildEventMenager.event.validation.ValidDateRange;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceivedEventDTO {
    @NotNull
    @Size(min = 3, max = 70)
    private String title;
    @Lob
    @Column(length = 1000)
    @Size(min = 3, max = 1000)
    private String description;
    @ValidDateRange
    private DateRange dateRange;
    @NotNull
    private boolean openToPublic;
    @org.hibernate.validator.constraints.UUID
    private String locationId;
    private List<String> organizers;

}
