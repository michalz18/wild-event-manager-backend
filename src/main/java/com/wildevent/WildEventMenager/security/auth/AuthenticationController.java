package com.wildevent.WildEventMenager.security.auth;

import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            @RequestBody RegisterRequest request
    ) {
        AuthenticationResponse response = service.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody RegisterRequest request
    ) {
        AuthenticationResponse response = service.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPasswordAfterCreatingUser(
            @RequestBody ResetPasswordRequest resetPasswordRequest
    ) {
        service.resetPassword(resetPasswordRequest);
        return new ResponseEntity<>("Password successfully reset.", HttpStatus.OK);
    }

    @PostMapping("/generate-reset-link")
    public ResponseEntity<String> generateResetPasswordLinkByUserRequest(
            @RequestBody ResetPasswordByUserRequest request
    ) {
        service.generateResetLink(request);
        return new ResponseEntity<>("Reset link sent", HttpStatus.OK);
    }
}

