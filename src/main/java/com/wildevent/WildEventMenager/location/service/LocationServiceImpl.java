package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationPointDTO;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final LocationPointDTOMapper locationPointDtoMapper;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, LocationPointDTOMapper locationPointDtoMapper) {
        this.locationRepository = locationRepository;
        this.locationPointDtoMapper = locationPointDtoMapper;
    }

    @Override
    public List<LocationPointDTO> getLocationPoints() {
        List<Location> locations = locationRepository.findAll();
        return locationPointDtoMapper.getLocationPointsDtoFromLocation(locations);
    }


}
