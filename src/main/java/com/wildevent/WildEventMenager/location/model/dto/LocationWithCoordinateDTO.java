package com.wildevent.WildEventMenager.location.model.dto;

import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;

import java.util.List;
import java.util.UUID;

public record LocationWithCoordinateDTO (UUID id, String title, String description, CoordinateDTO coordinateDTO, List<WildUserDTO> keepers){
}
