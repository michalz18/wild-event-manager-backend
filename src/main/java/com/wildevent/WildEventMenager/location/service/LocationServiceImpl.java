package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationPointDto;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final LocationPointDtoMapper locationPointDtoMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationPointDtoMapper locationPointDtoMapper) {
        this.locationRepository = locationRepository;
        this.locationPointDtoMapper = locationPointDtoMapper;
    }

    @Override
    public List<LocationPointDto> getLocationPoints() {
        List<Location> locations = locationRepository.findAll();
        return locationPointDtoMapper.getLocationPointsDtoFromLocation(locations);
    }


}
