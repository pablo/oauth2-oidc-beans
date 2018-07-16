package com.roshka.oauth2.objects;

public class AccessTokenRequest {

    public static final String GRANT_TYPE = "grant_type";
    public static final String CODE = "code";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String CLIENT_ID = "client_id";

    // REQUIRED. Value MUST be set to "authorization_code".
    private String grantType;

    // REQUIRED. The authorization code received from the authorization server.
    private String code;

    // REQUIRED, if the "redirect_uri" parameter was included in the
    //         authorization request as described in Section 4.1.1, and their
    //         values MUST be identical.
    private String redirectURI;

    // REQUIRED, if the client is not authenticating with the
    //         authorization server as described in Section 3.2.1.
    private String clientId;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
