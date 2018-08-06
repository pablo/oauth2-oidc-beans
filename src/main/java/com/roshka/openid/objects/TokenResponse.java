package com.roshka.openid.objects;

import com.roshka.oauth2.objects.AccessTokenResponse;

public class TokenResponse extends AccessTokenResponse {

    private String idToken;

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
