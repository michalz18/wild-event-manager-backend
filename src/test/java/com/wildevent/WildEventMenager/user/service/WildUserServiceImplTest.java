package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.service.LocationService;
import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.service.RoleService;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.model.ReceivedWildUserDTO;
import com.wildevent.WildEventMenager.user.service.email.EmailSendingService;
import com.wildevent.WildEventMenager.user.service.password.PasswordGeneratorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private PasswordGeneratorService passwordGeneratorService;

    @Mock
    private LocationService locationService;

    @Mock
    private RoleService roleService;

    @Mock
    private EmailSendingService emailSendingService;

    public WildUserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UUID roleId = UUID.randomUUID();
        UUID locationId = UUID.randomUUID();
        Role role = new Role();
        Location location = new Location();

        when(roleService.mapRolesFromIds(anySet())).thenReturn(Collections.singleton(role));
        when(locationService.mapLocationsFromIds(anyList())).thenReturn(Collections.singletonList(location));
        when(passwordGeneratorService.generatePassword()).thenReturn("password");
        when(wildUserRepository.save(any())).thenReturn(new WildUser());

        ReceivedWildUserDTO userDTO = new ReceivedWildUserDTO();
        userDTO.setLocationIds(Collections.singletonList(String.valueOf(locationId)));
        userDTO.setRoleIds(Collections.singleton(String.valueOf(roleId)));
        userDTO.setName("John");
        userDTO.setEmail("john@example.com");
        userDTO.setPhone("123456789");

        boolean result = wildUserService.createUser(userDTO);

        assertTrue(result);
        verify(emailSendingService, times(1)).sendWelcomeEmail(anyString(), anyString());
        verify(wildUserRepository, times(1)).save(any(WildUser.class));
    }

    @Test
    void testShouldUpdateUser() {
        UUID userId = UUID.randomUUID();
        WildUser existingUser = new WildUser();
        ReceivedWildUserDTO dto = new ReceivedWildUserDTO();

        when(wildUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        wildUserService.updateUser(userId, dto);

        verify(wildUserRepository).save(existingUser);
    }

    @Test
    void testShouldUpdateUserRoleAndLocation() {
        UUID userId = UUID.randomUUID();
        WildUser existingUser = new WildUser();
        ReceivedWildUserDTO dto = new ReceivedWildUserDTO();
        dto.setRoleIds(Collections.singleton("roleId"));
        dto.setLocationIds(Collections.singletonList("locationId"));

        Role role = new Role();
        Location location = new Location();
        Set<Role> newRoles = Collections.singleton(role);
        List<Location> newLocations = Collections.singletonList(location);

        when(wildUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(roleService.mapRolesFromIds(Collections.singleton("roleId"))).thenReturn(newRoles);
        when(locationService.mapLocationsFromIds(Collections.singletonList("locationId"))).thenReturn(newLocations);

        wildUserService.updateUser(userId, dto);

        assertEquals(newRoles, existingUser.getRole());
        assertEquals(newLocations, existingUser.getLocation());
    }



    @Test
    void testDeactivateUserShouldReturnTrueWhenUserExists() {
        UUID userId = UUID.randomUUID();
        WildUser wildUser = mock(WildUser.class);
        List<Event> events = Collections.emptyList();
        when(wildUser.getEventOrganized()).thenReturn(events);
        when(wildUserRepository.findById(userId)).thenReturn(Optional.of(wildUser));

        verify(wildUser).setActive(false);
        verify(wildUserRepository).save(wildUser);
    }

    @Test
    public void testDeactivateUserShouldReturnFalseIfUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(wildUserRepository.findById(userId)).thenReturn(Optional.empty());


        verify(wildUserRepository, never()).save(any());
    }
}