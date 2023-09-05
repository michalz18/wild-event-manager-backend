package com.wildevent.WildEventMenager.map.service;

import com.wildevent.WildEventMenager.map.model.Map;
import com.wildevent.WildEventMenager.map.model.dto.MapDTO;
import com.wildevent.WildEventMenager.map.model.dto.MapLocationsDTO;
import com.wildevent.WildEventMenager.map.repository.MapRepository;
import com.wildevent.WildEventMenager.map.service.dtoMapper.MapDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {

    private final MapRepository mapRepository;

    private final MapDTOMapper mapDTOMapper;

    public MapServiceImpl(MapRepository mapRepository, MapDTOMapper mapDTOMapper) {
        this.mapRepository = mapRepository;
        this.mapDTOMapper = mapDTOMapper;
    }

    @Override
    public MapDTO getMapWithLocationPoints() {
        Map map = mapRepository.findFirstByCurrentIsTrue();
        return mapDTOMapper.getMapDTO(map);
    }

    @Override
    public MapLocationsDTO getMapWithLocations() {
        Map map = mapRepository.findFirstByCurrentIsTrue();
        return mapDTOMapper.getMapLocationsDTO(map);
    }
}
