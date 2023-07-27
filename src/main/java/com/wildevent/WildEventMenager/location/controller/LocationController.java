package com.wildevent.WildEventMenager.location.controller;

<<<<<<< HEAD
import com.wildevent.WildEventMenager.location.model.LocationDTO;
import com.wildevent.WildEventMenager.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:3000")
=======
import com.wildevent.WildEventMenager.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/"))
>>>>>>> development
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
<<<<<<< HEAD

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLocationById(@PathVariable UUID id) {
        LocationDTO locationDTO = locationService.getLocationById(id);
        try {
            if (locationDTO != null) {
                return ResponseEntity.ok().body(locationDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }
}
=======
}
>>>>>>> development
