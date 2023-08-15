package com.wildevent.WildEventMenager.user.service.dtoMapper;

import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;

public interface UserDTOMapper {
    WildUserDTO getUserDtoFromWildUser(WildUser wildUser);
}
