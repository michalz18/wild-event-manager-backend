package com.wildevent.WildEventMenager.map.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import com.wildevent.WildEventMenager.map.model.Map;
import com.wildevent.WildEventMenager.map.model.dto.MapDTO;
import com.wildevent.WildEventMenager.map.model.dto.MapLocationsDTO;
import com.wildevent.WildEventMenager.map.model.dto.ReceivedMapDTO;
import com.wildevent.WildEventMenager.map.repository.MapRepository;
import com.wildevent.WildEventMenager.map.service.dtoMapper.MapDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    private final MapRepository mapRepository;

    private final MapDTOMapper mapDTOMapper;

    private final LocationRepository locationRepository;

    public MapServiceImpl(MapRepository mapRepository, MapDTOMapper mapDTOMapper, LocationRepository locationRepository) {
        this.mapRepository = mapRepository;
        this.mapDTOMapper = mapDTOMapper;
        this.locationRepository = locationRepository;
    }

    @Override
    public MapDTO getMapWithLocationPoints() {
        Map map = mapRepository.findFirstByCurrentIsTrue();
        List<Location> locations = locationRepository.getAllByMap(map);
        map.setLocations(locations);
        return mapDTOMapper.getMapDTO(map);
    }

    @Override
    public MapLocationsDTO getMapWithLocations() {
        Map map = mapRepository.findFirstByCurrentIsTrue();
        List<Location> locations = locationRepository.getAllByMap(map);
        return mapDTOMapper.getMapLocationsDTO(map, locations);
    }
    @Transactional
    @Override
    public void saveMap(ReceivedMapDTO dto) {
        Map oldMap = mapRepository.findFirstByCurrentIsTrue();
        if (oldMap != null) {
            mapRepository.updateCurrentToFalse(oldMap.getId());
        }
        mapRepository.save(mapDTOMapper.getMapFromReceivedDTO(dto));
        saveOldLocations(oldMap);
    }

    public void saveOldLocations(Map oldMap) {
        Map newMap = mapRepository.findFirstByCurrentIsTrue();
        locationRepository.switchLocationsMap(oldMap, newMap);
    }
}