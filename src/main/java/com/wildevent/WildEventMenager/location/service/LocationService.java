package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.LocationPointDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wildevent.WildEventMenager.location.model.LocationDTO;

import java.util.Optional;
import java.util.UUID;

@Service
public interface LocationService {
    List<LocationPointDTO> getLocationPoints();
    Optional<LocationDTO> getLocationById(UUID id);

}


