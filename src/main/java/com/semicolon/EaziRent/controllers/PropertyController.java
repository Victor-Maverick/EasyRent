package com.semicolon.EaziRent.controllers;

import com.semicolon.EaziRent.data.models.Property;
import com.semicolon.EaziRent.dtos.requests.AddPropertyRequest;
import com.semicolon.EaziRent.dtos.responses.ViewPropertyResponse;
import com.semicolon.EaziRent.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping(path = "/add", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addProperty(@ModelAttribute AddPropertyRequest request,
                                         Principal principal) throws IOException {
        String email = principal.getName();
        return ResponseEntity.status(CREATED).body(propertyService.addProperty(request, email));
    }

    @GetMapping("/all")
    public ResponseEntity<?> allProperties(){
        ViewPropertyResponse response =  propertyService.findAll();
        return ResponseEntity.ok(response);
    }

}
