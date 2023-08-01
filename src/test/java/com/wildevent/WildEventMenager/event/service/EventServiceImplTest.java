package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.EventDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class EventServiceImplTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventDTOMapper eventDTOMapper;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testGetEventById_ValidId_ReturnEventDTO() {
        UUID eventId = UUID.randomUUID();
        Event event = new Event();
        event.setId(eventId);
        EventDTO expectedEventDTO = new EventDTO("Sample Event",
                "Event description",
                LocalDateTime.of(2023, 7, 27, 10, 0),
                LocalDateTime.of(2023, 7, 27, 12, 0),
                "Title"
        );
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventDTOMapper.getEventDtoFromEvent(event)).thenReturn(expectedEventDTO);


        Optional<EventDTO> resultEventDTO = eventService.getEventById(eventId);


        assertEquals(expectedEventDTO, resultEventDTO.orElse(null));
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventDTOMapper, times(1)).getEventDtoFromEvent(event);
    }

    @Test
    public void testGetEventById_InvalidId_ExceptionThrow() {
        UUID invalidId = UUID.randomUUID();
        when(eventRepository.findById(invalidId)).thenReturn(Optional.empty());


        Optional<EventDTO> resultEventDTO = eventService.getEventById(invalidId);


        assertFalse(resultEventDTO.isPresent());
        verify(eventRepository, times(1)).findById(invalidId);
    }
}