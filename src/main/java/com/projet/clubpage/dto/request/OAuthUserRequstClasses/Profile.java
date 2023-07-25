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
        "thumbnail_image_url",
        "profile_image_url",
        "is_default_image"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class Profile {

    @JsonProperty("nickname")
    public String nickname;
    @JsonProperty("thumbnail_image_url")
    public String thumbnailImageUrl;
    @JsonProperty("profile_image_url")
    public String profileImageUrl;
    @JsonProperty("is_default_image")
    public Boolean isDefaultImage;

}