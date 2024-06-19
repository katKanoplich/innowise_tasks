package com.programming.techie.controller;

import com.programming.techie.dto.auth.AuthenticationRequest;
import com.programming.techie.dto.auth.AuthenticationResponse;
import com.programming.techie.dto.registry.RegisterRequestAdmin;
import com.programming.techie.dto.registry.RegisterRequestUser;
import com.programming.techie.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/client")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequestUser request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerTransporter(@RequestBody RegisterRequestAdmin request) {
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
