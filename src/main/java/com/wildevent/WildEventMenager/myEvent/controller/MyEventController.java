package com.wildevent.WildEventMenager.myEvent.controller;

import com.wildevent.WildEventMenager.myEvent.model.MyEventDTO;
import com.wildevent.WildEventMenager.myEvent.service.MyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/my-events")
public class MyEventController {
    private final MyEventService myEventService;

    @Autowired
    public MyEventController(MyEventService myEventService) {
        this.myEventService = myEventService;
    }

    @GetMapping("/event")
    public List<MyEventDTO> getAllActiveEventsForLoggedInUser() {
        return myEventService.getAllActiveEventsForLoggedInUser();
    }
}
