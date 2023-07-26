package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationDTO;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetLocationById_ExistingId_ReturnsLocationDTO() {
        // Arrange
        UUID existingId = UUID.randomUUID();
        String title = "Test Location";
        String description = "Test Description";
        Location location = new Location();
        location.setId(existingId);
        location.setTitle(title);
        location.setDescription(description);

        when(locationRepository.findById(existingId)).thenReturn(Optional.of(location));

        // Act
        LocationDTO locationDTO = locationService.getLocationById(existingId);

        // Assert
        assertNotNull(locationDTO);
        assertEquals(existingId, locationDTO.id());
        assertEquals(title, locationDTO.title());
        assertEquals(description, locationDTO.description());

        // Verify that locationRepository.findById() was called once with the correct id
        verify(locationRepository, times(1)).findById(existingId);
    }

    @Test
    void testGetLocationById_NonExistingId_ReturnsNull() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        when(locationRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act
        LocationDTO locationDTO = locationService.getLocationById(nonExistingId);

        // Assert
        assertNull(locationDTO);

        // Verify that locationRepository.findById() was called once with the correct id
        verify(locationRepository, times(1)).findById(nonExistingId);
    }
}