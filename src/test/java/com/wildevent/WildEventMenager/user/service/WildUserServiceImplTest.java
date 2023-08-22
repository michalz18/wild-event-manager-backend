package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WildUserServiceImplTest {
    @InjectMocks
    private WildUserServiceImpl wildUserService;
    @Mock
    WildUserRepository wildUserRepository;
    @Mock
    EventRepository eventRepository;

    public WildUserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testDeactivateUserShouldReturnTrueWhenUserExists() {
        UUID userId = UUID.randomUUID();
        WildUser wildUser = mock(WildUser.class);
        List<Event> events = Collections.emptyList();
        when(wildUser.getEventOrganized()).thenReturn(events);
        when(wildUserRepository.findById(userId)).thenReturn(Optional.of(wildUser));

        boolean result = wildUserService.deactivateUser(userId);

        assertTrue(result);
        verify(wildUser).setActive(false);
        verify(wildUserRepository).save(wildUser);
    }

    @Test
    public void testDeactivateUserShouldReturnFalseIfUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(wildUserRepository.findById(userId)).thenReturn(Optional.empty());

        boolean result = wildUserService.deactivateUser(userId);

        assertFalse(result);
        verify(wildUserRepository, never()).save(any());
    }

}