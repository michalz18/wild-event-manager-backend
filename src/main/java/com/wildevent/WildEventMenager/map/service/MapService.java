package com.wildevent.WildEventMenager.map.service;

import com.wildevent.WildEventMenager.map.model.dto.MapDTO;
import com.wildevent.WildEventMenager.map.model.dto.MapLocationsDTO;
import com.wildevent.WildEventMenager.map.model.dto.ReceivedMapDTO;
import org.springframework.stereotype.Service;

@Service
public interface MapService {
    MapDTO getMapWithLocationPoints();

    MapLocationsDTO getMapWithLocations();

    void saveMap(ReceivedMapDTO dto);
}
