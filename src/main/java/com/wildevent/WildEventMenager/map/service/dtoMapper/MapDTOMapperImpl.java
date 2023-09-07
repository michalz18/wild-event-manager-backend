package com.wildevent.WildEventMenager.map.service.dtoMapper;

import com.wildevent.WildEventMenager.coordinate.model.Coordinate;
import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import com.wildevent.WildEventMenager.coordinate.service.CoordinateDTOMapper;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationWithCoordinateDTO;
import com.wildevent.WildEventMenager.location.service.dtoMappers.LocationDTOMapper;
import com.wildevent.WildEventMenager.map.model.Map;
import com.wildevent.WildEventMenager.map.model.dto.MapDTO;
import com.wildevent.WildEventMenager.map.model.dto.MapLocationsDTO;
import com.wildevent.WildEventMenager.map.model.dto.ReceivedMapDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MapDTOMapperImpl implements MapDTOMapper {

    private final CoordinateDTOMapper coordinateDTOMapper;
    private final LocationDTOMapper locationDTOMapper;

    public MapDTOMapperImpl(CoordinateDTOMapper coordinateDTOMapper, LocationDTOMapper locationDTOMapper) {
        this.coordinateDTOMapper = coordinateDTOMapper;
        this.locationDTOMapper = locationDTOMapper;
    }

    @Override
    public MapDTO getMapDTO(Map map) {
        UUID id = map.getId();
        CoordinateDTO coordinateDTO = coordinateDTOMapper.getCoordinateDTO(map.getCoordinate());
        double zoom = map.getZoom();
        int bearing = map.getBearing();
        List<LocationPointDTO> locations = locationDTOMapper.getLocationPointsDtoFromLocation(map.getLocations());
        return new MapDTO(id, coordinateDTO, zoom, bearing, locations);
    }



    @Override
    public MapLocationsDTO getMapLocationsDTO(Map map) {
        UUID id = map.getId();
        CoordinateDTO coordinateDTO = coordinateDTOMapper.getCoordinateDTO(map.getCoordinate());
        double zoom = map.getZoom();
        int bearing = map.getBearing();
        List<LocationWithCoordinateDTO> locations = locationDTOMapper.getLocationWithCoordinateDTO(map.getLocations());
        return new MapLocationsDTO(id, coordinateDTO, zoom, bearing, locations);
    }

    @Override
    public Map getMapFromReceivedDTO(ReceivedMapDTO dto) {
        return new Map(
                new Coordinate(dto.getLatitude(), dto.getLongitude()),
                dto.getZoom(),
                dto.getBearing(),
                true
        );
    }
}

