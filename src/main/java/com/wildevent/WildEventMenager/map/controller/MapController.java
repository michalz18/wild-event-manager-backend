package com.wildevent.WildEventMenager.map.controller;

import com.wildevent.WildEventMenager.map.model.dto.MapDTO;
import com.wildevent.WildEventMenager.map.model.dto.MapLocationsDTO;
import com.wildevent.WildEventMenager.map.service.MapService;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MapController {

    private final static String MAP_URL = "/map";
    private final static String NO_AUTH_MAP_URL = AccessUrlProvider.NO_AUTH + MAP_URL;

    private final static String AUTH_MAP_URL = AccessUrlProvider.AUTH + MAP_URL;

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping(value = NO_AUTH_MAP_URL)
    public ResponseEntity<MapDTO> getMapWithLocationPoints() {
            return ResponseEntity.ok().body(mapService.getMapWithLocationPoints());
    }

    @GetMapping(value = AUTH_MAP_URL)
    public ResponseEntity<MapLocationsDTO> getMap() {
        return ResponseEntity.ok().body(mapService.getMapWithLocations());
    }
}
