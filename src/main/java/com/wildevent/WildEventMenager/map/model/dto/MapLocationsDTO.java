package com.wildevent.WildEventMenager.map.model.dto;

import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationWithCoordinateDTO;

import java.util.List;
import java.util.UUID;

public record MapLocationsDTO (UUID id, CoordinateDTO coordinate, double zoom, int bearing, List<LocationWithCoordinateDTO> locations) {
}
