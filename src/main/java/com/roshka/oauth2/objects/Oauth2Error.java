package com.roshka.oauth2.objects;

public class Oauth2Error {

    public enum Oauth2ErrorType {
        INVALID_REQUEST("invalid_request"),
        UNAUTHORIZED_CLIENT("unauthorized_client"),
        ACCESS_DENIED("access_denied"),
        UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type"),
        INVALID_SCOPE("invalid_scope"),
        SERVER_ERROR("server_error"),
        TEMPORARILY_UNAVAILABLE("temporarily_unavailable")
        ;

        private String errValue;

        Oauth2ErrorType(String errValue) {
            this.errValue = errValue;
        }

        public String getErrValue()
        {
            return this.errValue;
        }

    }

    public Oauth2Error(Oauth2ErrorType error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }


    private Oauth2ErrorType error;
    private String errorDescription;
    private String errorURI;
    private String state;

    public Oauth2ErrorType getError() {
        return error;
    }

    public void setError(Oauth2ErrorType error) {
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

}
