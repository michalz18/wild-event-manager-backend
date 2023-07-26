package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationDTO;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDTO getLocationById(UUID id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            return new LocationDTO(
                    location.getId(),
                    location.getTitle(),
                    location.getDescription()
            );
        }
        return null;
    }
}
