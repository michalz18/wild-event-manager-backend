package com.wildevent.WildEventMenager.user.controller;

import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.service.WildUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class WildUserController {
    private final static String ACTIVE_STAFF_URL = "/staff";
    private final static String STAFF_MANAGEMENT_ACTIVE_STAFF_URL = AccessUrlProvider.STAFF_MANAGEMENT + ACTIVE_STAFF_URL;
    private final WildUserService wildUserService;
    @Autowired
    public WildUserController(WildUserService wildUserService) {
        this.wildUserService = wildUserService;
    }

    @GetMapping(value = STAFF_MANAGEMENT_ACTIVE_STAFF_URL)
    public List<WildUserDTO> getAllActiveUsers() {
        return wildUserService.getAllActiveUsers();
    }
}
