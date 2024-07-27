package com.autenticationservice.autenticationservice.auth;

import com.autenticationservice.autenticationservice.config.AuthenticationService;
import com.autenticationservice.autenticationservice.dto.AuthenticationResponse;
import com.autenticationservice.autenticationservice.dto.AuthhenticateRequest;
import com.autenticationservice.autenticationservice.dto.ReqisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthcantionController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody ReqisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthhenticateRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }



}
