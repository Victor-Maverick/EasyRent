package com.semicolon.EaziRent.services.impls;

import com.semicolon.EaziRent.config.MailConfig;
import com.semicolon.EaziRent.dtos.requests.BrevoMailRequest;
import com.semicolon.EaziRent.dtos.requests.Recipient;
import com.semicolon.EaziRent.dtos.requests.SendMailRequest;
import com.semicolon.EaziRent.dtos.requests.Sender;
import com.semicolon.EaziRent.dtos.responses.BrevoMailResponse;
import com.semicolon.EaziRent.services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor
public class EaziMailService implements MailService {
    private final MailConfig mailConfig;
    static String SENDER_EMAIL = "eazirent@gmail.com";

    @Override
    public String sendMail(SendMailRequest sendMailRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = mailConfig.getMailApiUrl();
        BrevoMailRequest request = new BrevoMailRequest();
        request.setSubject("Registration successful");
        request.setSender(new Sender());
        request.setRecipients(
                List.of(
                        new Recipient(sendMailRequest.getRecipientEmail(), sendMailRequest.getRecipientName())
                ));
        request.setContent(sendMailRequest.getContent());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set("api-key", mailConfig.getMailApiKey());
        headers.set("accept", APPLICATION_JSON.toString());
        RequestEntity<?> httpRequest
                = new RequestEntity<>(request, headers, HttpMethod.POST, URI.create(url));
        ResponseEntity<BrevoMailResponse> response = restTemplate.postForEntity(url, httpRequest, BrevoMailResponse.class);
        if(response.getBody() != null
                && response.getStatusCode()== HttpStatusCode.valueOf(201))
            return "mail sent successfully";
        else throw new RuntimeException("email not sent");


    }

}
