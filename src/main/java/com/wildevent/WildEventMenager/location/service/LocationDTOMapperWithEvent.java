package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;

import java.util.List;

public interface LocationDTOMapperWithEvent {
    List<EventTitleDTO> getTodayEvents();
}
