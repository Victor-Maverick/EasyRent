package com.semicolon.EaziRent.services;

import com.semicolon.EaziRent.dtos.requests.RegisterRequest;
import com.semicolon.EaziRent.dtos.responses.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RenterServiceTest {
    @Autowired
    private RenterService renterService;

    @Test
    public void testRegisterRenter(){
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("first name");
        request.setLastName("last name");
        request.setEmail("test@email.com");
        request.setPassword("password");
        RegisterResponse response = renterService.register(request);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
    }
}