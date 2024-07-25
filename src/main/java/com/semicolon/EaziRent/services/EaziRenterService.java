package com.semicolon.EaziRent.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.semicolon.EaziRent.data.models.BioData;
import com.semicolon.EaziRent.data.models.Renter;
import com.semicolon.EaziRent.data.repositories.RenterRepository;
import com.semicolon.EaziRent.dtos.requests.RegisterRequest;
import com.semicolon.EaziRent.dtos.responses.RegisterResponse;
import com.semicolon.EaziRent.dtos.responses.UpdateDataResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.semicolon.EaziRent.data.constants.Role.RENTER;

@Service
@AllArgsConstructor
public class EaziRenterService implements RenterService{
    private final RenterRepository renterRepository;
    private final ModelMapper modelMapper;
    private final BioDataService bioDataService;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        request.setRole(RENTER);
        BioData data = bioDataService.register(request);
        Renter renter = modelMapper.map(request, Renter.class);
        renter.setOccupation(request.getOccupation());
        renter.setBioData(data);
        renter = renterRepository.save(renter);
        RegisterResponse response = modelMapper.map(renter, RegisterResponse.class);
        response.setMessage("Renter successfully registered");
        return response;
    }


    @Override
    @Transactional
    public UpdateDataResponse update(Long renterId, JsonPatch jsonPatch) {
        Renter renter = renterRepository.findById(renterId).orElseThrow();
        BioData bioData = renter.getBioData();
        UpdateDataResponse response = bioDataService.update(bioData.getId(), jsonPatch);
        bioData = bioDataService.findBioDataBy(bioData.getId());
        renter.setBioData(bioData);
        renterRepository.save(renter);
        return response;
    }


}
