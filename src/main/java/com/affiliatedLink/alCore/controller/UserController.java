package com.affiliatedLink.alCore.controller;

import com.affiliatedLink.alCore.entity.User;
import com.affiliatedLink.alCore.event.RegistrationCompleteEvent;
import com.affiliatedLink.alCore.exception.UserNotFoundException;
import com.affiliatedLink.alCore.model.AuthenticationRequest;
import com.affiliatedLink.alCore.model.AuthenticationResponse;
import com.affiliatedLink.alCore.model.RegisterRequest;
import com.affiliatedLink.alCore.service.AuthenticationService;
import com.affiliatedLink.alCore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest registerRequest, final HttpServletRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(
                authenticationResponse.getUser(),
                applicationUrl(request)
        ));
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }

    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest){
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authRequest);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
