package com.projet.clubpage.service;

import com.projet.clubpage.entity.OAuthAttributes;
import com.projet.clubpage.entity.SessionUser;
import com.projet.clubpage.entity.User;
import com.projet.clubpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        log.info(user.getRole().getKey());

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());

    }

//    private User saveOrUpdate(OAuthAttributes attributes){
//        User user = userRepository.findOneByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes))
//                    .orElse(attributes.toEntity());
//        return userRepository.save(user);
//
//    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        Optional<User> optionalUser = userRepository.findOneByEmail(attributes.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.update(attributes); // update 메서드 호출하여 엔티티 업데이트
            return userRepository.save(user);
        } else {
            return userRepository.save(attributes.toEntity());
        }
    }
}
