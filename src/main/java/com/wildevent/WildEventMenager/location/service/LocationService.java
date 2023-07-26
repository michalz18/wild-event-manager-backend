package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface LocationService {
    LocationDTO getLocationById(UUID id);
}
