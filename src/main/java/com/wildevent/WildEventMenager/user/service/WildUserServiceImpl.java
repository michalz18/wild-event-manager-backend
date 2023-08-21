package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.service.dtoMapper.ReceivedWildUserDTO;
import com.wildevent.WildEventMenager.user.service.dtoMapper.UserDTOMapper;
import com.wildevent.WildEventMenager.user.service.email.EmailService;
import com.wildevent.WildEventMenager.user.service.password.PasswordGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WildUserServiceImpl implements WildUserService {
    private final WildUserRepository wildUserRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public WildUserServiceImpl
            (
                    WildUserRepository wildUserRepository,
                    UserDTOMapper userDTOMapper
            ) {
        this.wildUserRepository = wildUserRepository;
        this.userDTOMapper = userDTOMapper;
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
        return null;
    }

    @Override
    public boolean deactivateUser(UUID userId) {
        Optional<WildUser> userOptional = wildUserRepository.findById(userId);
        if (userOptional.isPresent()) {
            WildUser user = userOptional.get();

            user.setActive(false);

            List<Event> eventsOrganizedByUser = user.getEventOrganized();
            for (Event event : eventsOrganizedByUser) {
                event.getOrganizer().remove(user);
            }

            wildUserRepository.save(user);
            return true;
        }
        return false;
    }
}

