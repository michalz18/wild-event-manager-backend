package com.wildevent.WildEventMenager.location.model.dto;

import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;

import java.util.UUID;

public record LocationWithCoordinateDTO (UUID id, String title, String description, CoordinateDTO coordinateDTO){
}
