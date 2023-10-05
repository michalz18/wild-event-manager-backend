package com.wildevent.WildEventMenager.myEvent.service;

import com.wildevent.WildEventMenager.myEvent.model.MyEvent;
import com.wildevent.WildEventMenager.myEvent.model.MyEventDTO;
import com.wildevent.WildEventMenager.myEvent.repository.MyEventRepository;
import com.wildevent.WildEventMenager.myEvent.service.dtoMapper.MyEventDTOMapper;
import com.wildevent.WildEventMenager.security.SecurityContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class MyEventServiceImpl implements MyEventService {
    private final MyEventRepository myEventRepository;
    private final MyEventDTOMapper myEventDTOMapper;
    private final SecurityContextProvider securityContextProvider;

    @Autowired
    public MyEventServiceImpl(MyEventRepository myEventRepository, MyEventDTOMapper myEventDTOMapper, SecurityContextProvider securityContextProvider) {
        this.myEventRepository = myEventRepository;
        this.myEventDTOMapper = myEventDTOMapper;
        this.securityContextProvider = securityContextProvider;
    }

    @Override
    public List<MyEventDTO> getAllActiveEventsForLoggedInUser() {
        UUID loggedInUserId = securityContextProvider.getLoggedInUserId();

        // Jeśli z jakiegoś powodu userId jest null, zwróć pustą listę
        if (loggedInUserId == null) {
            return Collections.emptyList();
        }

        List<MyEvent> events = myEventRepository.findDistinctByLocationWildUserIdOrOrganizerId(loggedInUserId);

        if (events == null || events.isEmpty()) {
            return Collections.emptyList();
        }

        return events.stream()
                .map(myEventDTOMapper::getEventDtoFromEvent)
                .distinct()
                .collect(Collectors.toList());
    }
}

