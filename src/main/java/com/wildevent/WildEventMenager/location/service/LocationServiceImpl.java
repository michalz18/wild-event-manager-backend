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
    private final LocationDTOMapper locationDTOMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, LocationDTOMapper locationDTOMapper) {
        this.locationRepository = locationRepository;
        this.locationDTOMapper = locationDTOMapper;
    }

    @Override
    public LocationDTO getLocationById(UUID id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            return locationDTOMapper.mapToDTO(location);
        }
        return null;
    }
}
