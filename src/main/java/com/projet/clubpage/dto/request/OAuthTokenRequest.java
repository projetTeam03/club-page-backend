package com.projet.clubpage.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthTokenRequest {

    private String id_token;
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
