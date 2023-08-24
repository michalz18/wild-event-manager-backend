package com.wildevent.WildEventMenager.location.controller;

import com.wildevent.WildEventMenager.location.model.dto.LocationDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationIdTitleDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;
import com.wildevent.WildEventMenager.location.service.LocationService;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LocationController {

    private final static String LOCATION_URL = "/location";
        private final static String LOCATIONS_URL = "/locations";
    private final static String NO_AUTH_LOCATION_URL = AccessUrlProvider.NO_AUTH + LOCATION_URL;
    private final static String AUTH_LOCATIONS_URL = AccessUrlProvider.AUTH + LOCATIONS_URL;
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = NO_AUTH_LOCATION_URL)
    public ResponseEntity<List<LocationPointDTO>> getLocationPoints() {
        return ResponseEntity.ok().body(locationService.getLocationPoints());
    }

    @GetMapping(value = NO_AUTH_LOCATION_URL + "/{id}")
    public ResponseEntity<Object> getLocationById(@PathVariable UUID id) {
        try {
            Optional<LocationDTO> optionalLocationDTO = locationService.getLocationById(id);
            return optionalLocationDTO
                    .<ResponseEntity<Object>>map(locationDTO -> ResponseEntity.ok().body(locationDTO))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

    @GetMapping(value = AUTH_LOCATIONS_URL)
    public ResponseEntity<List<LocationIdTitleDTO>> getAllLocations() {
        try {
            return ResponseEntity.ok().body(locationService.getAllLocations());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}