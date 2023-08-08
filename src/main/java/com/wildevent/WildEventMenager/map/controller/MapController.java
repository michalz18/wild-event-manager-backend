package com.wildevent.WildEventMenager.map.controller;

import com.wildevent.WildEventMenager.map.repository.MapRepositoryImpl;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MapController {

    private final static String MAP_URL = "/map";
    private final static String NO_AUTH_MAP_URL = AccessUrlProvider.NO_AUTH + MAP_URL;

    private final MapRepositoryImpl mapRepositoryImpl;

    public MapController(MapRepositoryImpl mapRepositoryImpl) {
        this.mapRepositoryImpl = mapRepositoryImpl;
    }

    @GetMapping(value = NO_AUTH_MAP_URL, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() {
        try {
            byte[] mapData = mapRepositoryImpl.getMap();
            return ResponseEntity.ok().body(mapData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
