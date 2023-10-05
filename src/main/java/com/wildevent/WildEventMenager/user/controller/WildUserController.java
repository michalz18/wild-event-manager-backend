package com.wildevent.WildEventMenager.user.controller;

import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.model.WildUserNameIdDTO;
import com.wildevent.WildEventMenager.user.service.WildUserService;
import com.wildevent.WildEventMenager.user.model.ReceivedWildUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<WildUserDTO>> getAllActiveUsers() {
        try {
            List<WildUserDTO> activeUsers = wildUserService.getAllActiveUsers();
            return new ResponseEntity<>(activeUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = STAFF_MANAGEMENT_ACTIVE_STAFF_URL)
    public ResponseEntity<String> addUser(@RequestBody ReceivedWildUserDTO userDTO) {
        boolean createdUser = wildUserService.createUser(userDTO);
        if (createdUser) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = STAFF_MANAGEMENT_ACTIVE_STAFF_URL + "/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody ReceivedWildUserDTO userDTO) {
        try {
            wildUserService.updateUser(userId, userDTO);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = STAFF_MANAGEMENT_ACTIVE_STAFF_URL + "/deactivate/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable UUID userId) {
        try {
            wildUserService.deactivateUser(userId);
            return new ResponseEntity<>("User deactivated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
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
