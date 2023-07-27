package com.wildevent.WildEventMenager.location.service;

<<<<<<< HEAD
import com.wildevent.WildEventMenager.location.model.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface LocationService {
    LocationDTO getLocationById(UUID id);
=======
import com.wildevent.WildEventMenager.location.ropository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
>>>>>>> development
}
