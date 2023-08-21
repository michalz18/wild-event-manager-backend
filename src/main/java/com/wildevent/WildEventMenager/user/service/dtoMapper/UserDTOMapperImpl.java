package com.wildevent.WildEventMenager.user.service.dtoMapper;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserDTOMapperImpl implements UserDTOMapper {
    @Override
    public WildUserDTO getUserDtoFromWildUser(WildUser wildUser) {
        List<String> roles = wildUser.getRole().stream().map(Role::getName).toList();
        List<String> locations = wildUser.getLocation().stream().map(Location::getTitle).toList();

        return new WildUserDTO(
                wildUser.getId(),
                wildUser.getName(),
                wildUser.getEmail(),
                wildUser.getPhone(),
                roles,
                locations
        );
    }
}
