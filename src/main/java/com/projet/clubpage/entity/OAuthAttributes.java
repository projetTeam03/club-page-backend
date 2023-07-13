package com.projet.clubpage.entity;

import com.projet.clubpage.security.Role;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private Role role;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.role = Role.USER;
    }

    public static OAuthAttributes of(String socialName, String userNameAttributeName, Map<String, Object> attributes) {
        if ("kakao".equals(socialName)) {
            return ofKakao("id", attributes);
        }
        return null;
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .deleted(0)
                .createDt(new Timestamp(System.currentTimeMillis()))
                .role(role)
                .build();
    }

}
