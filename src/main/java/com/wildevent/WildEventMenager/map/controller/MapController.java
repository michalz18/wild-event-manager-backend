package com.wildevent.WildEventMenager.map.controller;

import com.wildevent.WildEventMenager.map.model.dto.MapDTO;
import com.wildevent.WildEventMenager.map.model.dto.MapLocationsDTO;
import com.wildevent.WildEventMenager.map.model.dto.ReceivedMapDTO;
import com.wildevent.WildEventMenager.map.service.MapService;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MapController {

    private final static String MAP_URL = "/map";
    private final static String NO_AUTH_MAP_URL = AccessUrlProvider.NO_AUTH + MAP_URL;
    private final static String AUTH_MAP_URL = AccessUrlProvider.AUTH + MAP_URL;
    private final static String MAP_CONFIG_MAP_URL = AccessUrlProvider.MAP_CONFIG + MAP_URL;

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

    @PostMapping(value = MAP_CONFIG_MAP_URL)
    public ResponseEntity<Object> saveMap(@Valid @RequestBody ReceivedMapDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                mapService.saveMap(dto);
            } catch (Error e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().build();
        }
    }
}
