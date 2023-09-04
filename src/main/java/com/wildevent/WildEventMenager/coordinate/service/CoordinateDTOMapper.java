package com.wildevent.WildEventMenager.coordinate.service;

import com.wildevent.WildEventMenager.coordinate.model.Coordinate;
import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import org.springframework.stereotype.Service;

@Service
public interface CoordinateDTOMapper {

    CoordinateDTO getCoordinateDTO(Coordinate coordinate);
}
