package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.service.EventService;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.service.LocationService;
import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.service.RoleService;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.model.WildUserNameIdDTO;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.model.ReceivedWildUserDTO;
import com.wildevent.WildEventMenager.user.service.dtoMapper.UserDTOMapper;
import com.wildevent.WildEventMenager.user.service.email.EmailSendingService;
import com.wildevent.WildEventMenager.user.service.password.PasswordGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WildUserServiceImpl implements WildUserService {
    private final WildUserRepository wildUserRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordGeneratorService passwordGeneratorService;
    private final LocationService locationService;
    private final RoleService roleService;
    private final EventService eventService;
    private final EmailSendingService emailSendingService;

    @Autowired
    public WildUserServiceImpl
            (
                    WildUserRepository wildUserRepository,
                    UserDTOMapper userDTOMapper,
                    PasswordGeneratorService passwordGeneratorService,
                    LocationService locationService,
                    RoleService roleService,
                    EventService eventService,
                    EmailSendingService emailSendingService
            )
    {
        this.wildUserRepository = wildUserRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordGeneratorService = passwordGeneratorService;
        this.locationService = locationService;
        this.roleService = roleService;
        this.eventService = eventService;
        this.emailSendingService = emailSendingService;
    }

    @Override
    public List<WildUserDTO> getAllActiveUsers() {
        return wildUserRepository.findAll().stream()
                .filter(WildUser::isActive)
                .map(userDTOMapper::getUserDtoFromWildUser)
                .toList();
    }

    @Transactional
    @Override
    public boolean createUser(ReceivedWildUserDTO userDTO) {
        String generatedPassword = passwordGeneratorService.generatePassword();

        List<Location> locations = locationService.mapLocationsFromIds(userDTO.getLocationIds());
        Set<Role> roles = roleService.mapRolesFromIds(userDTO.getRoleIds());

        WildUser wildUser = WildUser.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .password(generatedPassword)
                .active(true)
                .location(locations)
                .role(roles)
                .build();

        wildUserRepository.save(wildUser);

        emailSendingService.sendWelcomeEmail(userDTO.getEmail(), generatedPassword);

        return true;
    }

    @Override
    public void updateUser(UUID userId, ReceivedWildUserDTO userDTO) {
        WildUser user = getUserById(userId);
        mapReceivedUserDtoToWildUser(user, userDTO);
        wildUserRepository.save(user);
    }

    @Override
    @Transactional
    public void deactivateUser(UUID userId) {
        WildUser user = getUserById(userId);
        wildUserRepository.deactivateUser(userId);

        List<Event> events = eventService.findAllEventsByOrganizer(userId);

        for (Event event : events) {
            event.getOrganizer().remove(user);
        }
    }

    private WildUser getUserById(UUID userId) {
        return wildUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    private void mapReceivedUserDtoToWildUser(WildUser user, ReceivedWildUserDTO userDTO) {
        List<Location> locations = locationService.mapLocationsFromIds(userDTO.getLocationIds());
        Set<Role> roles = roleService.mapRolesFromIds(userDTO.getRoleIds());

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(roles);
        user.setLocation(locations);

    @Override
    public List<WildUserNameIdDTO> getAllUsers() {
        return wildUserRepository.findAll().stream()
                .filter(WildUser::isActive)
                .map(userDTOMapper::getUserNameIdDtoFromWildUser)
                .toList();
    }
}

