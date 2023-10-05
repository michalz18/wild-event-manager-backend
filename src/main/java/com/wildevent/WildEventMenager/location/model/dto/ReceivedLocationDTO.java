package com.wildevent.WildEventMenager.location.model.dto;

import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import com.wildevent.WildEventMenager.coordinate.validation.ValidCoordinate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReceivedLocationDTO {

    private UUID id;
    @NotNull
    @Size(min = 3, max = 50)
    private String title;
    @Size(min = 3, max = 1_000)
    private String description;
    @NotNull
    private double longitude;
    @NotNull
    private double latitude;
    private List<String> wildUserIds;
}
