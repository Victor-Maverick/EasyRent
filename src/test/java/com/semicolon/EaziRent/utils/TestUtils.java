package com.semicolon.EaziRent.utils;

import com.semicolon.EaziRent.dtos.requests.AddPropertyRequest;
import com.semicolon.EaziRent.dtos.requests.AddressRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.semicolon.EaziRent.data.constants.State.LAGOS;
import static com.semicolon.EaziRent.data.constants.Type.HOSTEL;

public class TestUtils {
    public static final String BLACKLISTED_TOKEN = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc" +
            "3MiOiJFYXN5UmVudEFwcCIsImlhdCI6MTcyMTkxMDU2NSwiZXhwIjoxNzIxOTk2OTY1LCJzdWIiOiJjb" +
            "2xlc2FtQGdtYWlsLmNvbSIsInByaW5jaXBhbCI6ImNvbGVzYW1AZ21haWwuY29tIiwiY3JlZGVudGlhb" +
            "HMiOiJbUFJPVEVDVEVEXSIsInJvbGVzIjpbIlJFTlRFUiJdfQ.E4ItFh9rCkBYn1DTrb1XnlP4_nIk" +
            "C_rnHsGOtaQcj4AvVaoYSvd4ydiyn7HUG1dAbNqLol8cicGn7zPhsZyC2fgDsNWB25xMzXFXo7uVGnze" +
            "JZ8o1boknxyBwO_tfwll7m7lEN3QhRT0Yly7BHKDm9QNj8h-J1hSLWnok5tifLfJe5nEfvGls0ZUmFwL" +
            "RAhTEvjREPPTamzglltNlI9FlK1XiorFav2193ZuDwhtd3y0vT6DgB_tMP1Ki5SwvfsAXi9_F456AH" +
            "8k_aF1S-3pSX-MN50wdE-FUiipveHmkH3sdMXQgh9yXKEVFKcLWv2qCAMU66Fu330bI6VIZqV3pQ";

    public static final String TEST_IMAGE_LOCATION = "C:\\Users\\DELL\\IdeaProjects\\EasyRent\\src\\main\\resources\\images\\juno.jpg";


    public static AddPropertyRequest buildPropertyRequest(InputStream inputStream) throws IOException {
        AddPropertyRequest request = new AddPropertyRequest();
        MultipartFile file = new MockMultipartFile("media", inputStream);
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setNumber("32");
        addressRequest.setStreet("Street");
        addressRequest.setArea("Area");
        addressRequest.setState(LAGOS);
        addressRequest.setLga("LGA");
        request.setAddressRequest(addressRequest);
        request.setMediaFile(file);
        request.setType(HOSTEL);
        request.setNoOfApartments(50);
        return request;
    }

}
