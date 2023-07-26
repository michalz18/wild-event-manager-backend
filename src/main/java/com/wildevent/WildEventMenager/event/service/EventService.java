package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.EventTitleDTO;

import java.util.List;

public interface EventService {
    List<EventTitleDTO> getTodayEvents();
}
