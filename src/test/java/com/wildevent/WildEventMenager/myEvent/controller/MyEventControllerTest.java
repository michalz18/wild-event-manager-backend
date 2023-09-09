//package com.wildevent.WildEventMenager.myEvent.controller;
//
//import com.wildevent.WildEventMenager.myEvent.model.MyEventDTO;
//import com.wildevent.WildEventMenager.myEvent.service.MyEventService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(MyEventController.class)
//public class MyEventControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MyEventService myEventService;
//
//    private MyEventDTO mockEventDTO;
//
//    @BeforeEach
//    void setUp() {
//        mockEventDTO = new MyEventDTO(UUID.randomUUID(), "Test Event", "Description", "Location", true, "Start Time", "End Time");
//    }
//
//    @Test
//    void shouldReturnListOfEvents() throws Exception {
//        List<MyEventDTO> eventDTOList = Collections.singletonList(mockEventDTO);
//
//        when(myEventService.getAllActiveEventsForLoggedInUser()).thenReturn(eventDTOList);
//
//        mockMvc.perform(get("/my-events/event"))
//                .with(SecurityMockMvcRequestPostProcessors.user("user"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Event"));
//    }
//
//    @Test
//    void shouldReturnEmptyListWhenNoEvents() throws Exception {
//        when(myEventService.getAllActiveEventsForLoggedInUser()).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/my-events/event"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
//    }
//}
