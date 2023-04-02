package com.affiliatedLink.alCore.service;

import com.affiliatedLink.alCore.model.AuthenticationRequest;
import com.affiliatedLink.alCore.model.AuthenticationResponse;
import com.affiliatedLink.alCore.model.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
