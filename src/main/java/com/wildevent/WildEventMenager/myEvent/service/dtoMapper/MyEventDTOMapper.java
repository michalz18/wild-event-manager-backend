package com.wildevent.WildEventMenager.myEvent.service.dtoMapper;

import com.wildevent.WildEventMenager.myEvent.model.MyEvent;
import com.wildevent.WildEventMenager.myEvent.model.MyEventDTO;

public interface MyEventDTOMapper {
    MyEventDTO getEventDtoFromEvent(MyEvent myEvent);
}
