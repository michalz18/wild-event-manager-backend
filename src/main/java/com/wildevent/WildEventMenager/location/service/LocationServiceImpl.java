package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationPointDTO;
import com.wildevent.WildEventMenager.location.model.LocationDTO;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import com.wildevent.WildEventMenager.location.service.dtoMappers.LocationDTOMapper;
import com.wildevent.WildEventMenager.location.service.dtoMappers.LocationPointDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationDTOMapper locationDTOMapper;
    private final LocationPointDTOMapper locationPointDtoMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, LocationDTOMapper locationDTOMapper, LocationPointDTOMapper locationPointDtoMapper) {
        this.locationRepository = locationRepository;
        this.locationDTOMapper = locationDTOMapper;
        this.locationPointDtoMapper = locationPointDtoMapper;
    }

    @Override
    public List<LocationPointDTO> getLocationPoints() {
        List<Location> locations = locationRepository.findAll();
        return locationPointDtoMapper.getLocationPointsDtoFromLocation(locations);
    }

    @Override
    public Optional<LocationDTO> getLocationById(UUID id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        return locationOptional.isPresent()
                ? locationOptional.map(locationDTOMapper::getLocationDtoFromLocation)
                : Optional.empty();
    }


}
