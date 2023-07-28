package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.EventDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.location.model.Coordinate;
import com.wildevent.WildEventMenager.location.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventServiceImpl eventServiceImpl;

    private UUID uuid;
    private Location location;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.uuid = UUID.randomUUID();
        this.location = new Location();
        this.location.setTitle("Sample Location");
        this.location.setDescription("Location description");


        Coordinate coordinate = new Coordinate();
        coordinate.setLocation(location);
        coordinate.setCoordinateX(123.45);
        coordinate.setCoordinateY(67.89);


        this.location.setCoordinate(coordinate);
    }

    @Test
    public void testGetEventById_ValidId_ReturnEventDTO() {
        Event mockEvent = new Event(
                uuid,
                "Sample Event",
                "Event description",
                LocalDateTime.of(2023, 7, 27, 10, 0),
                LocalDateTime.of(2023, 7, 27, 12, 0),
                location,
                true,
                null,
                null
        );
        Mockito.when(eventRepository.findById(uuid)).thenReturn(Optional.of(mockEvent));


        EventDTO eventDTO = eventServiceImpl.getEventById(uuid).get();


        assertEquals("Sample Event", eventDTO.title());
        assertEquals("Event description", eventDTO.description());
        assertEquals(LocalDateTime.of(2023, 7, 27, 10, 0), eventDTO.startsAt());
        assertEquals(LocalDateTime.of(2023, 7, 27, 12, 0), eventDTO.endsAt());
        assertEquals("Sample Location", eventDTO.location());

    }

    @Test
    public void testGetEventById_InvalidId_ExceptionThrow() {
        Mockito.when(eventRepository.findById(uuid)).thenReturn(Optional.empty());


        assertThrows(IllegalArgumentException.class, () -> {
            eventServiceImpl.getEventById(uuid);
        });
    }
}