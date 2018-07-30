package com.roshka.oauth2.objects;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Oauth2Error {

    public static final String INVALID_REQUEST = "invalid_request";
    public static final String UNAUTHORIZED_CLIENT = "unauthorized_client";
    public static final String ACCESS_DENIED = "access_denied";
    public static final String UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";
    public static final String INVALID_SCOPE = "invalid_scope";
    public static final String SERVER_ERROR = "server_error";
    public static final String TEMPORARILY_UNAVAILABLE = "temporarily_unavailable";

    public Oauth2Error(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    private String error;
    private String errorDescription;
    private String errorURI;
    private String state;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorURI() {
        return errorURI;
    }

    public void setErrorURI(String errorURI) {
        this.errorURI = errorURI;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String buildErrorRedirect(String redirectURI)
    {
        StringBuilder sb = new StringBuilder();

        sb.append(redirectURI).append("?");

        // error
        sb.append("error=").append(getError()).append('&');
        try {
            if (getErrorDescription() != null)
                sb.append("error_description=").append(
                        URLEncoder.encode(getErrorDescription(), "utf-8")
                ).append('&');
            if (getErrorURI() != null)
                sb.append("error_uri=").append(
                        URLEncoder.encode(getErrorURI(), "utf-8")
                ).append('&');
            if (getState() != null)
                sb.append("state=").append(
                        URLEncoder.encode(getState(), "utf-8")
                );
        } catch (UnsupportedEncodingException e) {
            // ignore
        }

        return sb.toString();
    }

}
