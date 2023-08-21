package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.service.dtoMapper.ReceivedWildUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface WildUserService {
    List<WildUserDTO> getAllActiveUsers();
    WildUserDTO createUser(ReceivedWildUserDTO userDTO);
    boolean deactivateUser(UUID userId);
}
