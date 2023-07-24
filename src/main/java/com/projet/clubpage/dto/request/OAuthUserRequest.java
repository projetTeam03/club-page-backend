package com.projet.clubpage.dto.request;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.projet.clubpage.dto.request.OAuthUserRequstClasses.KakaoAccount;
import com.projet.clubpage.dto.request.OAuthUserRequstClasses.Properties;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nickname",
        "profile_image",
        "thumbnail_image"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class OAuthUserRequest {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("connected_at")
    public String connectedAt;
    @JsonProperty("properties")
    public Properties properties;
    @JsonProperty("kakao_account")
    public KakaoAccount kakaoAccount;

}