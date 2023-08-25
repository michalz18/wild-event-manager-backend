package com.wildevent.WildEventMenager.user.service.dtoMapper;

import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.model.WildUserNameIdDTO;

public interface UserDTOMapper {
    WildUserDTO getUserDtoFromWildUser(WildUser wildUser);

    WildUserNameIdDTO getUserNameIdDtoFromWildUser(WildUser wildUser);
}
