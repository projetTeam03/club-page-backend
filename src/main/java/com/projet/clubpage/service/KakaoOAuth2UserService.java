package com.projet.clubpage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.clubpage.dto.request.OAuthUserRequest;
import com.projet.clubpage.entity.User;
import com.projet.clubpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KakaoOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient auth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(), oAuth2AuthenticationToken.getName());
        OAuth2AccessToken accessToken = auth2AuthorizedClient.getAccessToken();

        //카카오 api 호출로 사용자 정보 가져오기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken.getTokenValue());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                OAuthUserRequest oAuthUserRequest = objectMapper.readValue(responseBody, OAuthUserRequest.class);
                Optional<User> user = userRepository.findById(oAuthUserRequest.getId());
                Set<OAuth2UserAuthority> authorities = getAuthorities(user.orElse(null));

                Map<String, Object> att = new HashMap<>();
                OAuth2User oAuth2User = new DefaultOAuth2User(authorities, att, "sub");
                return oAuth2User;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }


        return null;
    }

    private Set<OAuth2UserAuthority> getAuthorities(User user) {
        String userId = String.valueOf(user.getId());
        String userNickname = user.getNickname();
        String userEmail = user.getEmail();
        String userProFilePicture = user.getProfilePicture();

        Map<String, Object> userAttributes = new HashMap<>();
        userAttributes.put("id", userId);
        userAttributes.put("nickname", userNickname);
        userAttributes.put("email", userEmail);
        userAttributes.put("picture", userProFilePicture);
        OAuth2UserAuthority authority = new OAuth2UserAuthority(userAttributes);

        Set<OAuth2UserAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return authorities;

    }

}
