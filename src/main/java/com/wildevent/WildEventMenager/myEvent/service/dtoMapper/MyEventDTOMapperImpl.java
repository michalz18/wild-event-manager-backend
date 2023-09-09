package com.wildevent.WildEventMenager.myEvent.service.dtoMapper;

import com.wildevent.WildEventMenager.myEvent.model.MyEvent;
import com.wildevent.WildEventMenager.myEvent.model.MyEventDTO;
import org.springframework.stereotype.Service;

@Service
public class MyEventDTOMapperImpl implements MyEventDTOMapper {
    @Override
    public MyEventDTO getEventDtoFromEvent(MyEvent event) {
        return new MyEventDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation().getTitle(),
                event.isOpenToPublic(),
                event.getStartsAt().toString(),
                event.getEndsAt().toString()
        );
    }
}
