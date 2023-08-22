package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.service.LocationService;
import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.service.RoleService;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.service.dtoMapper.ReceivedWildUserDTO;
import com.wildevent.WildEventMenager.user.service.dtoMapper.UserDTOMapper;
import com.wildevent.WildEventMenager.user.service.email.EmailSendingService;
import com.wildevent.WildEventMenager.user.service.password.PasswordGeneratorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WildUserServiceImpl implements WildUserService {
    private final WildUserRepository wildUserRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordGeneratorService passwordGeneratorService;
    private final LocationService locationService;
    private final RoleService roleService;
    private final EmailSendingService emailSendingService;

    @Autowired
    public WildUserServiceImpl
            (
                    WildUserRepository wildUserRepository,
                    UserDTOMapper userDTOMapper,
                    PasswordGeneratorService passwordGeneratorService,
                    LocationService locationService,
                    RoleService roleService,
                    EmailSendingService emailSendingService
            ) {
        this.wildUserRepository = wildUserRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordGeneratorService = passwordGeneratorService;
        this.locationService = locationService;
        this.roleService = roleService;
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

        WildUser wildUser = mapToWildUser(userDTO, generatedPassword, locations, roles);

        wildUserRepository.save(wildUser);

        emailSendingService.sendWelcomeEmail(userDTO.getEmail(), generatedPassword);

        return true;
    }

    @Override
    public boolean deactivateUser(UUID userId) {
        Optional<WildUser> userOptional = wildUserRepository.findById(userId);
        if (userOptional.isPresent()) {
            WildUser user = userOptional.get();
            user.setActive(false);

            for (Event event : user.getEventOrganized()) {
                event.getOrganizer().remove(user);
            }
            user.setEventOrganized(new ArrayList<>());

            for (Location location : user.getLocation()) {
                location.getWildUser().remove(user);
            }
            user.setLocation(new ArrayList<>());

            wildUserRepository.save(user);
            return true;
        }
        return false;
    }

    private WildUser mapToWildUser(ReceivedWildUserDTO userDTO, String generatedPassword, List<Location> locations, Set<Role> roles) {
        WildUser wildUser = new WildUser();
        wildUser.setName(userDTO.getName());
        wildUser.setEmail(userDTO.getEmail());
        wildUser.setPhone(userDTO.getPhone());
        wildUser.setPassword(generatedPassword);
        wildUser.setActive(true);
        wildUser.setLocation(locations);
        wildUser.setRole(roles);
        return wildUser;
    }

}

