package com.semicolon.EaziRent.controllers;

import com.semicolon.EaziRent.dtos.requests.AddPropertyRequest;
import com.semicolon.EaziRent.dtos.responses.EaziRentAPIResponse;
import com.semicolon.EaziRent.dtos.responses.PropertyResponse;
import com.semicolon.EaziRent.dtos.responses.ViewPropertyResponse;
import com.semicolon.EaziRent.exceptions.EasyRentBaseException;
import com.semicolon.EaziRent.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
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
        try {
            String email = principal.getName();
            var response = propertyService.addProperty(request, email);
            return ResponseEntity.status(CREATED).body(new EaziRentAPIResponse<>(true, response));
        } catch (EasyRentBaseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> allProperties(){
        ViewPropertyResponse response =  propertyService.findAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/findBy{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            PropertyResponse response = propertyService.findBy(id);
            return ResponseEntity.ok(response);
        }
        catch (EasyRentBaseException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
