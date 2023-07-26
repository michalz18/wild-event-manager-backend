package com.wildevent.WildEventMenager.location.controller;

import com.wildevent.WildEventMenager.location.model.LocationPointDTO;
import com.wildevent.WildEventMenager.location.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    List<LocationPointDTO> getLocationPoints() {
        return locationService.getLocationPoints();
    }



}
