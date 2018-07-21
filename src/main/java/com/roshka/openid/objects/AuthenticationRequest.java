package com.roshka.openid.objects;

import com.roshka.oauth2.objects.AuthorizationRequest;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationRequest
    extends AuthorizationRequest {

    public static final String NONCE = "nonce";
    public static final String DISPLAY = "display";
    public static final String PROMPT = "prompt";
    public static final String MAX_AGE = "max_age";
    public static final String UI_LOCALES = "ui_locales";
    public static final String CLAIM_LOCALES = "claim_locales";
    public static final String ID_TOKEN_HINT = "id_token_hint";
    public static final String LOGIN_HINT = "login_hint";
    public static final String ACR_VALUES = "acr_values";

    private String nonce;
    private String display;
    private String prompt;
    private String maxAge;
    private String uiLocales;
    private String claimLocales;
    private String idTokenHint;
    private String loginHint;
    private String acrValues;
    private Map<String, String> additionalParams;

    public AuthenticationRequest()
    {
        additionalParams = new HashMap<String, String>();
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getUiLocales() {
        return uiLocales;
    }

    public void setUiLocales(String uiLocales) {
        this.uiLocales = uiLocales;
    }

    public String getIdTokenHint() {
        return idTokenHint;
    }

    public void setIdTokenHint(String idTokenHint) {
        this.idTokenHint = idTokenHint;
    }

    public String getLoginHint() {
        return loginHint;
    }

    public void setLoginHint(String loginHint) {
        this.loginHint = loginHint;
    }

    public String getAcrValues() {
        return acrValues;
    }

    public void setAcrValues(String acrValues) {
        this.acrValues = acrValues;
    }

    public Map<String, String> getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(Map<String, String> additionalParams) {
        this.additionalParams = additionalParams;
    }

    public String getClaimLocales() {
        return claimLocales;
    }

    public void setClaimLocales(String claimLocales) {
        this.claimLocales = claimLocales;
    }

    // The Authentication Request contains the prompt parameter with the value login.
    // In this case, the Authorization Server MUST reauthenticate the End-User even
    // if the End-User is already authenticated.
    public boolean mustAuthenticate() {
        return prompt != null && prompt.equals("login");
    }

    // The Authentication Request contains the prompt parameter with the value none.
    // In this case, the Authorization Server MUST return an error if an End-User
    // is not already Authenticated or could not be silently Authenticated.
    public boolean mustNotAuthenticate() {
        return prompt != null && prompt.equals("none");
    }
}
