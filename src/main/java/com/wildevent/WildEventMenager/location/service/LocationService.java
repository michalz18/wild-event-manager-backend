package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.LocationIdTitleDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;
import com.wildevent.WildEventMenager.location.model.dto.ReceivedLocationDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wildevent.WildEventMenager.location.model.dto.LocationDTO;

import java.util.Optional;
import java.util.UUID;

@Service
public interface LocationService {
    List<LocationPointDTO> getLocationPoints();
    Optional<LocationDTO> getLocationById(UUID id);

    List<Location> mapLocationsFromIds(List<String> locationIds);
    List<LocationIdTitleDTO> getAllLocations();

    void saveLocation(ReceivedLocationDTO locationDTO);

    void updateLocation(ReceivedLocationDTO locationDTO);

    boolean deleteLocationById(UUID id);
}


