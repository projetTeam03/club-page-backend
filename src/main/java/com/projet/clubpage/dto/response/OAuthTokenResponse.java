package com.projet.clubpage.dto.response;

import com.projet.clubpage.dto.request.OAuthTokenRequest;
import com.projet.clubpage.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthTokenResponse {

    private User user;
    private OAuthTokenRequest oAuthTokenRequest;

}
