package com.projet.clubpage.dto.request.OAuthUserRequstClasses;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "profile_nickname_needs_agreement",
        "profile_image_needs_agreement",
        "profile",
        "has_email",
        "email_needs_agreement",
        "is_email_valid",
        "is_email_verified",
        "email"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class KakaoAccount {

    @JsonProperty("profile_nickname_needs_agreement")
    public Boolean profileNicknameNeedsAgreement;
    @JsonProperty("profile_image_needs_agreement")
    public Boolean profileImageNeedsAgreement;
    @JsonProperty("profile")

    public Profile profile;
    @JsonProperty("has_email")
    public Boolean hasEmail;
    @JsonProperty("email_needs_agreement")
    public Boolean emailNeedsAgreement;
    @JsonProperty("is_email_valid")
    public Boolean isEmailValid;
    @JsonProperty("is_email_verified")
    public Boolean isEmailVerified;
    @JsonProperty("email")
    public String email;

}