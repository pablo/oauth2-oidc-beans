package com.roshka.oauth2.objects;

import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.stream.Stream;

public class AuthorizationRequest {

    public enum FlowType {
        AUTHORIZATION_CODE_FLOW,
        IMPLICIT_FLOW,
        HYBRID_FLOW,
        INVALID
    }

    public static final String RESPONSE_TYPE = "response_type";
    public static final String CLIENT_ID = "client_id";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String SCOPE = "scope";
    public static final String STATE = "state";
    public static final String RESPONSE_MODE = "response_mode";

    private String responseType;
    private String clientId;
    private String redirectURI;
    private String scope;
    private String state;
    private String responseMode;

    private String[] responseTypeValues;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
        this.responseTypeValues = responseType.split("\\s+");
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResponseMode() {
        return responseMode;
    }

    public void setResponseMode(String responseMode) {
        this.responseMode = responseMode;
    }

    public String[] getResponseTypeValues() {
        return responseTypeValues;
    }

    public void setResponseTypeValues(String[] responseTypeValues) {
        this.responseTypeValues = responseTypeValues;
    }

    public FlowType getFlowType()
    {
        if (getResponseTypeValues() == null)
            return FlowType.INVALID;

        FlowType ret = FlowType.INVALID;

        boolean code = Arrays.stream(getResponseTypeValues()).anyMatch(v -> v.equals("code"));
        boolean id_token = Arrays.stream(getResponseTypeValues()).anyMatch(v -> v.equals("id_token"));
        boolean token = Arrays.stream(getResponseTypeValues()).anyMatch(v -> v.equals("token"));

        if (code && !id_token && !token) {
            ret = FlowType.AUTHORIZATION_CODE_FLOW;
        } else if (id_token && !code && !token) {
            ret = FlowType.IMPLICIT_FLOW;
        } else if (id_token && token && !code) {
            ret = FlowType.IMPLICIT_FLOW;
        } else if (code && id_token && !token) {
            ret = FlowType.HYBRID_FLOW;
        } else if (code && token && !id_token) {
            ret = FlowType.HYBRID_FLOW;
        } else if (code && token && id_token) {
            ret = FlowType.HYBRID_FLOW;
        }

        return ret;

    }

    public String getSuccessfullCodeRedirect()
    {
        return String.format("%s?code=%s&state=%s", getRedirectURI(), "" ,getState());
    }

    public String getSuccessfullTokenRedirect()
    {
        return null;

    }

    public String getSuccessfullHybridRedirect()
    {
        return null;

    }

    public String getSuccessfullRedirect()
    {
        if (getResponseType().equals("code")) {
            return getSuccessfullCodeRedirect();
        }

        return "";
    }
}
