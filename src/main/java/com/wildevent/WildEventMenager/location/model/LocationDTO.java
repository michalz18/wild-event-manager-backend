package com.wildevent.WildEventMenager.location.model;

import com.wildevent.WildEventMenager.event.model.EventTitleDTO;

import java.util.List;
import java.util.UUID;

public record LocationDTO(UUID id, String title, String description, List<EventTitleDTO> eventTitles) {

}
