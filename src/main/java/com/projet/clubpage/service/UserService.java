package com.projet.clubpage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.clubpage.dto.request.OAuthUserRequest;
import com.projet.clubpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long getUserIndex(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
                String responseBody = response.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    OAuthUserRequest oAuthUserRequest = objectMapper.readValue(responseBody, OAuthUserRequest.class);
                    return oAuthUserRequest.getId();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            if (e.getMessage().startsWith("401")) {
                return 401L;
            } else if (e.getMessage().startsWith("404")) {
                return 404L;
            } else if (e.getMessage().startsWith("500")) {
                return 500L;
            }
        }
        




        return null;

    }


}
