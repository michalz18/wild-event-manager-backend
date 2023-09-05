package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.LocationDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationIdTitleDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import com.wildevent.WildEventMenager.location.service.dtoMappers.LocationDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<LocationPointDTO> getLocationPoints() {
        List<Location> locations = locationRepository.findAll();
        return locationDTOMapper.getLocationPointsDtoFromLocation(locations);
    }

    @Override
    public Optional<LocationDTO> getLocationById(UUID id) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.withHour(23).withMinute(59).withSecond(59);
        return locationRepository.findLocationWithEventsBetweenDates(id, now, endOfDay)
                .map(locationDTOMapper::getLocationDtoFromLocation);
    }

    @Override
    public List<LocationIdTitleDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(locationDTOMapper::getLocationIdTitleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> mapLocationsFromIds(List<String> locationIds) {
        return locationIds.stream()
                .map(UUID::fromString)
                .flatMap(uuid -> locationRepository.findById(uuid).stream())
                .collect(Collectors.toList());
    }
}
