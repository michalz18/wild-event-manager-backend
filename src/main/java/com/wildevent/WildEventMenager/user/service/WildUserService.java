package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.model.ReceivedWildUserDTO;
import com.wildevent.WildEventMenager.user.model.WildUserNameIdDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface WildUserService {
    List<WildUserDTO> getAllActiveUsers();
    boolean createUser(ReceivedWildUserDTO userDTO);
    void updateUser(UUID userId, ReceivedWildUserDTO userDTO);
    void deactivateUser(UUID userId);
    List<WildUserNameIdDTO> getAllUsers();
}
