package com.wildevent.WildEventMenager.event.controller;

import com.wildevent.WildEventMenager.event.service.EventServiceImpl;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@WebMvcTest(EventController.class)
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl eventService;

    @Test
    public void testDeleteEventWithValidId() throws Exception {
        UUID productId = UUID.randomUUID();
        String EVENT_URL = "/event";
        String EVENT_MANAGEMENT_EVENT_URL = AccessUrlProvider.EVENT_MANAGEMENT + EVENT_URL;

        mockMvc.perform(MockMvcRequestBuilders.delete(EVENT_MANAGEMENT_EVENT_URL + "/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(eventService, times(1)).deleteEventById(productId);
    }

}