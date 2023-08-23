package com.wildevent.WildEventMenager.user.controller;

import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.model.WildUserNameIdDTO;
import com.wildevent.WildEventMenager.user.service.WildUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class WildUserController {
    private final static String ACTIVE_STAFF_URL = "/staff";
    private final static String ACTIVE_USER_NAMES_URL = "/user/names";
    private final static String USER_NAMES_ID_URL = AccessUrlProvider.AUTH + ACTIVE_USER_NAMES_URL;
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

    @GetMapping(value = USER_NAMES_ID_URL)
    public ResponseEntity<List<WildUserNameIdDTO>> getAllUsers() {
        try {
            return ResponseEntity.ok().body(wildUserService.getAllUsers());
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
