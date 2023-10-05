package com.wildevent.WildEventMenager.myEvent.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.myEvent.model.MyEvent;
import com.wildevent.WildEventMenager.myEvent.model.MyEventDTO;
import com.wildevent.WildEventMenager.myEvent.repository.MyEventRepository;
import com.wildevent.WildEventMenager.myEvent.service.dtoMapper.MyEventDTOMapper;
import com.wildevent.WildEventMenager.security.SecurityContextProvider;
import com.wildevent.WildEventMenager.user.model.WildUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyEventServiceImplTest {

    @Mock
    private MyEventRepository myEventRepository;
    @Mock
    private MyEventDTOMapper myEventDTOMapper;

    @Mock
    private SecurityContextProvider securityContextProvider;

    @InjectMocks
    private MyEventServiceImpl myEventService;


    @Test
    void shouldReturnEventsForLoggedInUser() {
        UUID mockUserId = UUID.randomUUID();
        MyEvent mockEvent = new MyEvent();
        mockEvent.setTitle("Test Event");
        mockEvent.setId(mockUserId);
        mockEvent.setDescription("Test Description");
        mockEvent.setLocation(new Location());
        mockEvent.setOpenToPublic(true);
        mockEvent.setStartsAt(LocalDateTime.of(2023, 8, 24, 10, 0));
        mockEvent.setEndsAt(LocalDateTime.of(2023, 8, 24, 14, 0));
        mockEvent.setOrganizer(new WildUser());

        MyEventDTO mockEventDTO = new MyEventDTO(
                mockEvent.getId(),
                mockEvent.getTitle(),
                mockEvent.getDescription(),
                mockEvent.getLocation().toString(),
                mockEvent.isOpenToPublic(),
                mockEvent.getStartsAt().toString(),
                mockEvent.getEndsAt().toString()
        );


        when(securityContextProvider.getLoggedInUserId()).thenReturn(mockUserId);
        when(myEventRepository.findDistinctByLocationWildUserIdOrOrganizerId(mockUserId)).thenReturn(Collections.singletonList(mockEvent));
        when(myEventDTOMapper.getEventDtoFromEvent(mockEvent)).thenReturn(mockEventDTO);

        List<MyEventDTO> results = myEventService.getAllActiveEventsForLoggedInUser();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).title()).isEqualTo("Test Event");
    }

    @Test
    void shouldHandleNoLoggedInUser() {
        when(securityContextProvider.getLoggedInUserId()).thenReturn(null);

        List<MyEventDTO> result = myEventService.getAllActiveEventsForLoggedInUser();

        assertTrue(result.isEmpty());
    }
}
