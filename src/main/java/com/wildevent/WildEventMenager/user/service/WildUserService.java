package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WildUserService {
    List<WildUserDTO> getAllActiveUsers();
}
