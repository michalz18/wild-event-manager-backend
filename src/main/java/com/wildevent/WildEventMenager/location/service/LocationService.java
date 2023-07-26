package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.LocationPointDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    List<LocationPointDto> getLocationPoints();
}
