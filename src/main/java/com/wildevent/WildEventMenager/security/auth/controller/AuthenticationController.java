package com.wildevent.WildEventMenager.security.auth.controller;

import com.wildevent.WildEventMenager.security.auth.service.AuthenticationService;
import com.wildevent.WildEventMenager.security.auth.dto.AuthenticationRequestDTO;
import com.wildevent.WildEventMenager.security.auth.dto.RegisterRequestDTO;
import com.wildevent.WildEventMenager.security.auth.dto.ResetPasswordByUserRequestDTO;
import com.wildevent.WildEventMenager.security.auth.dto.ResetPasswordRequestDTO;
import com.wildevent.WildEventMenager.security.auth.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/no-auth/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        AuthenticationResponse response = service.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
           @Valid @RequestBody AuthenticationRequestDTO request
            ) {
        AuthenticationResponse response = service.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPasswordAfterCreatingUser(
            @Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO
    ) {
        service.resetPassword(resetPasswordRequestDTO);
        return new ResponseEntity<>("Password successfully reset.", HttpStatus.OK);
    }

    @PostMapping("/generate-reset-link")
    public ResponseEntity<String> generateResetPasswordLinkByUserRequest(
            @Valid @RequestBody ResetPasswordByUserRequestDTO request
    ) {
        service.generateResetLink(request);
        return new ResponseEntity<>("Reset link sent", HttpStatus.OK);
    }
}

