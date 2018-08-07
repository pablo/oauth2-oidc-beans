package com.roshka.oauth2.objects;

import com.roshka.openid.objects.AuthenticationRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AuthorizationResponse {

    private String code;
    private String state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String buildRedirect(String redirectURI) {
        StringBuilder sb = new StringBuilder();
        sb.append(redirectURI).append("?");
        try {
            sb
            .append("code=")
            .append(URLEncoder.encode(getCode(), "utf-8"))
            .append('&')
            .append("state=").append(URLEncoder.encode(getState(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            // ignore
        }
        return sb.toString();
    }

}
