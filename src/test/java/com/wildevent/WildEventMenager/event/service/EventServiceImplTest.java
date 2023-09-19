package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceImplTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventDTOMapper eventDTOMapper;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private WildUserRepository wildUserRepository;

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
                "Title", List.of("John"), true
        );
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventDTOMapper.getEventDtoFromEvent(event)).thenReturn(expectedEventDTO);


        Optional<EventDTO> resultEventDTO = eventService.getEventById(eventId);


        assertEquals(expectedEventDTO, resultEventDTO.orElse(null));
        assertTrue(resultEventDTO.isPresent());
        assertEquals("Sample Event", resultEventDTO.get().title());

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

    @Test
    public void testDeleteEventWithInvalidIdShouldReturnException() {
        UUID nonExistentEventId = UUID.randomUUID();


        try {
            eventService.deleteEventById(nonExistentEventId);
        } catch (NoSuchElementException exception) {
            assertEquals("Event not found", exception.getMessage());
        }


        verify(eventRepository, never()).deleteById(any());

    }

    @Test
    public void testFindAllEventsByOrganizer() {
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
        when(eventRepository.findAll()).thenReturn(eventList);


        List<EventDTO> result = eventService.getAllAcceptedEvents();


        assertEquals(2, result.size());
        assertEquals(event1, eventList.get(0));
        assertEquals(event2, eventList.get(1));

        verify(eventRepository, times(1)).findAll();

    }

    @Test
    public void testAddEvent_LocationNotFound_ThrowsEntityNotFoundException() {
        ReceivedEventDTO dto = new ReceivedEventDTO();
        dto.setLocationId(UUID.randomUUID().toString());
        dto.setOrganizers(List.of(UUID.randomUUID().toString()));


        when(locationRepository.findById(any())).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> eventService.addEvent(dto));

        verify(wildUserRepository, never()).findById(any());
        verify(eventRepository, never()).save(any());

    }

    @Test
    public void testGetAllAcceptedEvents() {
        Event event = new Event();
        Event event2 = new Event();
        List<Event> allEvents = List.of(event, event2);
        EventDTO expectedEventDTO1 = new EventDTO("Sample Event",
                "Event description",
                LocalDateTime.of(2023, 7, 27, 10, 0),
                LocalDateTime.of(2023, 7, 27, 12, 0),
                "Title", List.of("John"), true
        );
        EventDTO expectedEventDTO2 = new EventDTO("Sample Event2",
                "Event description2",
                LocalDateTime.of(2023, 7, 27, 11, 0),
                LocalDateTime.of(2023, 7, 27, 12, 0),
                "Title2", List.of("Adam"), true
        );
        List<EventDTO> eventDTOS = List.of(expectedEventDTO1, expectedEventDTO2);
        when(eventRepository.findAll()).thenReturn(allEvents);
        when(eventDTOMapper.getEventDtoFromEvent(event)).thenReturn(expectedEventDTO1);
        when(eventDTOMapper.getEventDtoFromEvent(event2)).thenReturn(expectedEventDTO2);


        List<EventDTO> result = eventService.getAllAcceptedEvents();


        assertEquals(eventDTOS.size(), result.size());

        verify(eventRepository, times(1)).findAll();
        verify(eventDTOMapper, times(1)).getEventDtoFromEvent(event);
        verify(eventDTOMapper, times(1)).getEventDtoFromEvent(event2);
    }

    @Test
    public void testGetTodayIncomingEvents_EventsToday() {
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> todayEvents = List.of(event1, event2);

        List<EventTitleDTO> expectedEventTitleDTOS = List.of(
                new EventTitleDTO(UUID.randomUUID(), "Title1", LocalDateTime.now(), UUID.randomUUID().toString()),
                new EventTitleDTO(UUID.randomUUID(), "Title2", LocalDateTime.now(), UUID.randomUUID().toString())
        );

        when(eventRepository.findAllByOpenToPublicIsTrueAndStartsAtBetweenOrderByStartsAtAsc(any(), any()))
                .thenReturn(todayEvents);
        when(eventDTOMapper.getEventTitlesDTOFromEvent(todayEvents)).thenReturn(expectedEventTitleDTOS);


        List<EventTitleDTO> result = eventService.getTodayIncomingEvents();


        assertEquals(expectedEventTitleDTOS.size(), result.size());
        assertEquals(expectedEventTitleDTOS, result);

        verify(eventRepository, times(1)).findAllByOpenToPublicIsTrueAndStartsAtBetweenOrderByStartsAtAsc(any(), any());
        verify(eventDTOMapper, times(1)).getEventTitlesDTOFromEvent(todayEvents);
    }

    @Test
    public void testGetTodayIncomingEvents_NoEventsToday() {
        when(eventRepository.findAllByOpenToPublicIsTrueAndStartsAtBetweenOrderByStartsAtAsc(any(), any()))
                .thenReturn(Collections.emptyList());


        List<EventTitleDTO> result = eventService.getTodayIncomingEvents();


        assertTrue(result.isEmpty());

        verify(eventRepository, times(1)).findAllByOpenToPublicIsTrueAndStartsAtBetweenOrderByStartsAtAsc(any(), any());
        verify(eventDTOMapper, times(1)).getEventTitlesDTOFromEvent(anyList());
    }
}