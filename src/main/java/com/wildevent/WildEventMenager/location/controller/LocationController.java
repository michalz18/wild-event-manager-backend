package com.wildevent.WildEventMenager.location.controller;

import com.wildevent.WildEventMenager.location.model.LocationDTO;
import com.wildevent.WildEventMenager.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable UUID id) {
        LocationDTO locationDTO = locationService.getLocationById(id);
        if (locationDTO != null) {
            return ResponseEntity.ok().body(locationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}