package com.wildevent.WildEventMenager.user.controller;

import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.service.WildUserService;
import com.wildevent.WildEventMenager.user.service.dtoMapper.ReceivedWildUserDTO;
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

    @PostMapping(value = STAFF_MANAGEMENT_ACTIVE_STAFF_URL)
    public @ResponseBody WildUserDTO addUser(@RequestBody ReceivedWildUserDTO userDTO) {
        return wildUserService.createUser(userDTO);
    }
    @DeleteMapping(value = STAFF_MANAGEMENT_ACTIVE_STAFF_URL + "/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable UUID userId) {
        try {
            wildUserService.deactivateUser(userId);
            return new ResponseEntity<>("User deactivated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
