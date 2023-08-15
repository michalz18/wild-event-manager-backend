package com.wildevent.WildEventMenager.user.service;

import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.service.dtoMapper.UserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WildUserServiceImpl implements WildUserService {
    private final WildUserRepository wildUserRepository;
    private final UserDTOMapper userDTOMapper;
    @Autowired
    public WildUserServiceImpl(WildUserRepository wildUserRepository, UserDTOMapper userDTOMapper) {
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
}
