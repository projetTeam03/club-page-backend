package com.projet.clubpage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.projet.clubpage.dto.request.OAuthTokenRequest;
import com.projet.clubpage.dto.request.OAuthUserRequest;
import com.projet.clubpage.dto.request.OAuthUserRequstClasses.KakaoAccount;
import com.projet.clubpage.dto.request.OAuthUserRequstClasses.Profile;
import com.projet.clubpage.dto.response.OAuthTokenResponse;
import com.projet.clubpage.entity.User;
import com.projet.clubpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {
    private final UserRepository userRepository;


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
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthUserRequest oAuthUserRequest = null;
        User user = new User();
        OAuthTokenResponse oAuthTokenResponse = new OAuthTokenResponse();
        response.getBody();
        
        try {
            oAuthUserRequest = objectMapper.readValue(response.getBody(), OAuthUserRequest.class);
            user.setId(oAuthUserRequest.getId());
            user.setEmail(oAuthUserRequest.getKakaoAccount().getEmail());
            user.setNickname(oAuthUserRequest.getProperties().getNickname());
            user.setProfilePicture(oAuthUserRequest.getProperties().getProfileImage());

            oAuthTokenResponse.setUser(user);
            oAuthTokenResponse.setOAuthTokenRequest(oAuthTokenRequest);

            if (userRepository.findById(user.getId()).isEmpty()) {
                userRepository.save(user);
            }


        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return oAuthTokenResponse;

//        gson.fromJson(response.getBody().value)
    }

}
