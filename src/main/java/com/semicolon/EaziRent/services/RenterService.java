package com.semicolon.EaziRent.services;

import com.semicolon.EaziRent.dtos.requests.RegisterRequest;
import com.semicolon.EaziRent.dtos.responses.RegisterResponse;

public interface RenterService {

    RegisterResponse register(RegisterRequest request);
}
