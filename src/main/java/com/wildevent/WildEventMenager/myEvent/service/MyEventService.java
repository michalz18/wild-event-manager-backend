package com.wildevent.WildEventMenager.myEvent.service;

import com.wildevent.WildEventMenager.myEvent.model.MyEventDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MyEventService {

    List<MyEventDTO> getAllActiveEventsForLoggedInUser();
}
