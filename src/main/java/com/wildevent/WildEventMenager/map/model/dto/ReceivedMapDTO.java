package com.wildevent.WildEventMenager.map.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReceivedMapDTO {


    @NotNull
    double longitude;
    @NotNull
    double latitude;
    @NotNull
    double zoom;
    @NotNull
    int bearing;

    @NotNull
    boolean transportLocations;
}
