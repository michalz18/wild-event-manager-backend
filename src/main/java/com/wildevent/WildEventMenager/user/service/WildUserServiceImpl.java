package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.repository.RoleRepository;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.service.dtoMapper.ReceivedWildUserDTO;
import com.wildevent.WildEventMenager.user.service.dtoMapper.UserDTOMapper;
import com.wildevent.WildEventMenager.user.service.email.EmailDetails;
import com.wildevent.WildEventMenager.user.service.email.EmailService;
import com.wildevent.WildEventMenager.user.service.password.PasswordGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WildUserServiceImpl implements WildUserService {
    private final WildUserRepository wildUserRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordGeneratorService passwordGeneratorService;
    private final LocationRepository locationRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    @Autowired
    public WildUserServiceImpl
            (
                    WildUserRepository wildUserRepository,
                    UserDTOMapper userDTOMapper,
                    PasswordGeneratorService passwordGeneratorService,
                    LocationRepository locationRepository,
                    RoleRepository roleRepository,
                    EmailService emailService
            ) {
        this.wildUserRepository = wildUserRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordGeneratorService = passwordGeneratorService;
        this.locationRepository = locationRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
    }

    @Override
    public List<WildUserDTO> getAllActiveUsers() {
        return wildUserRepository.findAll().stream()
                .filter(WildUser::isActive)
                .map(userDTOMapper::getUserDtoFromWildUser)
                .toList();
    }

    @Override
    public WildUserDTO createUser(ReceivedWildUserDTO userDTO) {
        String generatedPassword = passwordGeneratorService.generatePassword();

        List<Location> locations = userDTO.getLocationIds().stream()
                .map(UUID::fromString)
                .flatMap(uuid -> locationRepository.findById(uuid).stream())
                .collect(Collectors.toList());

        Set<Role> roles = userDTO.getRoleIds().stream()
                .map(UUID::fromString)
                .flatMap(uuid -> roleRepository.findById(uuid).stream())
                .collect(Collectors.toSet());

        WildUser wildUser = new WildUser();
        wildUser.setName(userDTO.getName());
        wildUser.setEmail(userDTO.getEmail());
        wildUser.setPhone(userDTO.getPhone());
        wildUser.setPassword(generatedPassword);
        wildUser.setActive(true);
        wildUser.setLocation(locations);
        wildUser.setRole(roles);

        WildUser savedUser = wildUserRepository.save(wildUser);

        EmailDetails emailDetails = new EmailDetails
                (
                        savedUser.getEmail(),
                        "Your password: " + generatedPassword,
                        "Welcome to Wild Event Management!"
                );
        emailService.sendSimpleMail(emailDetails);

        return userDTOMapper.getUserDtoFromWildUser(savedUser);
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


}

