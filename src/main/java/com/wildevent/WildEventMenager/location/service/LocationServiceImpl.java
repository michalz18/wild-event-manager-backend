package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationPointDto;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<LocationPointDto> getLocationPoints() {
        return locationRepository.findAll()
                .stream()
                .map(this::getLocationPointDtoFromLocation)
                .toList();
    }

    public LocationPointDto getLocationPointDtoFromLocation(Location location) {
        return new LocationPointDto(
                location.getId(),
                location.getCoordinate().getCoordinateX(),
                location.getCoordinate().getCoordinateY());
    }
}
