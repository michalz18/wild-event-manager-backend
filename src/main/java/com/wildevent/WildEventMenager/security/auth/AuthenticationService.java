package com.wildevent.WildEventMenager.security.auth;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.service.LocationService;
import com.wildevent.WildEventMenager.role.model.Role;
import com.wildevent.WildEventMenager.role.service.RoleService;
import com.wildevent.WildEventMenager.security.config.JwtService;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import com.wildevent.WildEventMenager.user.service.email.EmailSendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final WildUserRepository wildUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSendingService emailSendingService;
    private final LocationService locationService;
    private final RoleService roleService;

    public AuthenticationResponse register(RegisterRequest request) {
        String randomPassword = generateRandomPassword(8);
        List<Location> locations = locationService.mapLocationsFromIds(request.getLocationIds());
        Set<Role> roles = roleService.mapRolesFromIds(request.getRoleIds());

        WildUser user = WildUser.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(randomPassword))
                .phone(request.getPhone())
                .location(locations)
                .role(roles)
                .build();

        wildUserRepository.save(user);
        String resetToken = jwtService.generatePasswordResetToken(user);

        emailSendingService.sendPasswordResetEmail(user.getEmail(), resetToken);

        return AuthenticationResponse.builder()
                .token("User created and email sent.")
                .build();
    }

    public AuthenticationResponse authenticate(RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        WildUser user = wildUserRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-+<>?";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

}

