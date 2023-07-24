package com.projet.clubpage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.projet.clubpage.dto.request.OAuthTokenRequest;
import com.projet.clubpage.dto.response.OAuthTokenResponse;
import com.projet.clubpage.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KakaoLoginService {
    public OAuthTokenRequest getTokenInfo(ResponseEntity<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenRequest oAuthTokenRequest = null;
        try {
            oAuthTokenRequest = objectMapper.readValue(response.getBody(), OAuthTokenRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return oAuthTokenRequest;
    }

    public OAuthTokenResponse saveUserResponseToken(ResponseEntity<String> response, OAuthTokenRequest oAuthTokenRequest) {
        Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        response.getBody();
        User.builder();
               return null;
//        gson.fromJson(response.getBody().value)
    }

}
