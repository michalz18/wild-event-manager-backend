package com.wildevent.WildEventMenager.location.model.dto;

import com.wildevent.WildEventMenager.location.validation.ValidCoordinate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ReceivedLocationDTO {
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    @Size(min = 3, max = 1_000)
    private String description;
    @NotNull
    @ValidCoordinate
    private CoordinateDTO coordinate;
    private List<String> wildUserIds;
}
