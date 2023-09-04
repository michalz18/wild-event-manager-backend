package com.wildevent.WildEventMenager.coordinate.service;

import com.wildevent.WildEventMenager.coordinate.model.Coordinate;
import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import org.springframework.stereotype.Service;

@Service
public class CoordinateDTOMapperImpl implements CoordinateDTOMapper {


    @Override
    public CoordinateDTO getCoordinateDTO(Coordinate coordinate) {
        return new CoordinateDTO(coordinate.getId(), coordinate.getLongitude(), coordinate.getLatitude());
    }
}
