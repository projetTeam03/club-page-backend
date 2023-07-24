package com.projet.clubpage.dto.request.OAuthUserRequstClasses;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
public class Properties {

    @JsonProperty("nickname")
    public String nickname;
    @JsonProperty("profile_image")
    public String profileImage;
    @JsonProperty("thumbnail_image")
    public String thumbnailImage;

}