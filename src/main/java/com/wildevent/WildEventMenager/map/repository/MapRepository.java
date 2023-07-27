package com.wildevent.WildEventMenager.map.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;

@Repository
public class MapRepository {
    public byte[] getMap() throws IOException {
        Resource imageResource = new ClassPathResource("map.jpg");
        return Files.readAllBytes(imageResource.getFile().toPath());
    }
}
