package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.service.EventService;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.service.LocationService;
import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.service.RoleService;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.model.ReceivedWildUserDTO;
import com.wildevent.WildEventMenager.user.service.dtoMapper.UserDTOMapper;
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
    private EventService eventService;
    @Mock
    private EmailSendingService emailSendingService;
    @Mock
    private UserDTOMapper userDTOMapper;

    public WildUserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllActiveUsersReturnsOnlyActiveUsers() {
        WildUser activeUser = new WildUser();
        activeUser.setActive(true);

        WildUser inactiveUser = new WildUser();
        inactiveUser.setActive(false);

        WildUserDTO activeUserDTO = userDTOMapper.getUserDtoFromWildUser(activeUser);
        when(wildUserRepository.findAll()).thenReturn(List.of(activeUser, inactiveUser));
        when(userDTOMapper.getUserDtoFromWildUser(activeUser)).thenReturn(activeUserDTO);

        List<WildUserDTO> result = wildUserService.getAllActiveUsers();

        assertEquals(1, result.size());
        assertTrue(result.contains(activeUserDTO));
    }

    @Test
    public void testGetAllActiveUsersReturnsAllActiveUsersWhenEveryoneIsActive() {
        WildUser activeUser1 = new WildUser();
        activeUser1.setActive(true);

        WildUser activeUser2 = new WildUser();
        activeUser2.setActive(true);

        WildUserDTO activeUserDTO1 = userDTOMapper.getUserDtoFromWildUser(activeUser1);
        WildUserDTO activeUserDTO2 = userDTOMapper.getUserDtoFromWildUser(activeUser2);

        when(wildUserRepository.findAll()).thenReturn(List.of(activeUser1, activeUser2));
        when(userDTOMapper.getUserDtoFromWildUser(activeUser1)).thenReturn(activeUserDTO1);
        when(userDTOMapper.getUserDtoFromWildUser(activeUser2)).thenReturn(activeUserDTO2);

        List<WildUserDTO> result = wildUserService.getAllActiveUsers();

        assertEquals(2, result.size());
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
    void testDeactivateUserShouldDeactivateExistingUser() {
        UUID id = UUID.randomUUID();
        WildUser wildUser = new WildUser();
        wildUser.setActive(true);
        when(wildUserRepository.findById(id)).thenReturn(Optional.of(wildUser));

        wildUserService.deactivateUser(id);

        verify(wildUserRepository).deactivateUser(id);
        verify(eventService).findAllEventsByOrganizer(id);
    }

    @Test
    void testThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(wildUserRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> wildUserService.deactivateUser(userId));
    }

    @Test
    void testDeactivateUserRemovesUserFromOrganizedEvents() {
        UUID userId = UUID.randomUUID();
        WildUser user = new WildUser();
        user.setId(userId);
        user.setActive(true);
        Event event = new Event();
        event.setOrganizer(new ArrayList<>(List.of(user)));

        when(wildUserRepository.findById(userId)).thenReturn(Optional.of(user));
        when(eventService.findAllEventsByOrganizer(userId)).thenReturn(List.of(event));

        wildUserService.deactivateUser(userId);

        assertFalse(event.getOrganizer().contains(user));
    }
}
