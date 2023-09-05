package com.wildevent.WildEventMenager.map.service.dtoMapper;

import com.wildevent.WildEventMenager.map.model.Map;
import com.wildevent.WildEventMenager.map.model.dto.MapDTO;
import com.wildevent.WildEventMenager.map.model.dto.MapLocationsDTO;

public interface MapDTOMapper {
    MapDTO getMapDTO(Map map);

    MapLocationsDTO getMapLocationsDTO(Map map);
}
