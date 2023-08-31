package com.wildevent.WildEventMenager.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wildevent.WildEventMenager.event.model.DateRange;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.service.EventServiceImpl;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {
    String EVENT_MANAGEMENT_EVENT_URL = AccessUrlProvider.EVENT_MANAGEMENT + "/event";
    private final static String NO_AUTH_EVENT_URL = AccessUrlProvider.NO_AUTH + "/event";

   private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testDeleteEventWithValidId() throws Exception {
        UUID eventId = UUID.randomUUID();


        mockMvc.perform(MockMvcRequestBuilders.delete(EVENT_MANAGEMENT_EVENT_URL + "/{id}", eventId))
                .andExpect(status().isOk());


        verify(eventService, times(1)).deleteEventById(eventId);
    }

    @Test
    public void testPostNewEventWithValidData() throws Exception {
        ReceivedEventDTO receivedEventDTO = new ReceivedEventDTO(
                "Example title",
                "Description",
                new DateRange(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                true,
                UUID.randomUUID().toString(),
                List.of(UUID.randomUUID().toString()));


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post(EVENT_MANAGEMENT_EVENT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receivedEventDTO))
                )
                .andExpect(status().isOk())
                .andReturn();


        String responseBody = result.getResponse().getContentAsString();
        assertEquals("Event created successfully", responseBody);

    }

    @Test
    public void testPostNewEventWithInvalidData() throws Exception {
        ReceivedEventDTO receivedEventDTO = new ReceivedEventDTO(
                "",
                "",
                new DateRange(LocalDateTime.now().minusHours(1), LocalDateTime.now()),
                true,
                UUID.randomUUID().toString(),
                List.of(UUID.randomUUID().toString()));


        mockMvc.perform(MockMvcRequestBuilders
                        .post(EVENT_MANAGEMENT_EVENT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receivedEventDTO))
                )
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testGetAllAcceptedEventsReturn200() throws Exception {
        when(eventService.getAllAcceptedEvents()).thenReturn(List.of(
                new EventDTO("Example title", "Description", LocalDateTime.now(), LocalDateTime.now().plusHours(1), UUID.randomUUID().toString()),
                new EventDTO("Example title2", "Description2", LocalDateTime.now(), LocalDateTime.now().plusHours(2), UUID.randomUUID().toString())
        ));


        mockMvc.perform(MockMvcRequestBuilders
                        .get(EVENT_MANAGEMENT_EVENT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"));

    }

    @Test
    public void testGetEventByIdReturn200AndProperEvent() {
        UUID eventId = UUID.randomUUID();
        EventDTO eventDTO = new EventDTO("Example title",
                "Description",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                eventId.toString());
        when(eventService.getEventById(eventId)).thenReturn(Optional.of(eventDTO));


        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get(NO_AUTH_EVENT_URL + "/{id}", eventId)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().json("{}"));
        } catch (Exception exception) {
            fail("Exception should not be thrown");

        }
    }

    @Test
    public void testGetEventByIdNonExistingId() {
        UUID invalidId = UUID.randomUUID();
        when(eventService.getEventById(invalidId)).thenReturn(Optional.empty());


        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get(NO_AUTH_EVENT_URL + "/{id}", invalidId)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isNotFound());
        } catch (Exception exception) {
            fail("Exception should not be thrown");

        }
    }

}