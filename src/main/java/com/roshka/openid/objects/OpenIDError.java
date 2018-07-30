package com.roshka.openid.objects;

import com.roshka.oauth2.objects.Oauth2Error;

public class OpenIDError extends Oauth2Error {

    // comments from http://openid.net/specs/openid-connect-core-1_0-final.html#AuthError

    // The Authorization Server requires End-User interaction of some form to proceed. This error MAY be returned when the prompt parameter value in the Authentication Request is none, but the Authentication Request cannot be completed without displaying a user interface for End-User interaction.
    public static final String INTERACTION_REQUIRED = "interaction_required";
    // The Authorization Server requires End-User authentication. This error MAY be returned when the prompt parameter value in the Authentication Request is none, but the Authentication Request cannot be completed without displaying a user interface for End-User authentication.
    private static final String LOGIN_REQUIRED = "login_required";
    // The End-User is REQUIRED to select a session at the Authorization Server. The End-User MAY be authenticated at the Authorization Server with different associated accounts, but the End-User did not select a session. This error MAY be returned when the prompt parameter value in the Authentication Request is none, but the Authentication Request cannot be completed without displaying a user interface to prompt for a session to use.
    private static final String ACCOUNT_SELECTION_REQUIRED = "account_selection_required";
    // The Authorization Server requires End-User consent. This error MAY be returned when the prompt parameter value in the Authentication Request is none, but the Authentication Request cannot be completed without displaying a user interface for End-User consent.
    private static final String CONSENT_REQUIRED = "consent_required";
    // The request_uri in the Authorization Request returns an error or contains invalid data.
    private static final String INVALID_REQUEST_URI = "invalid_request_uri";
    // The request parameter contains an invalid Request Object.
    private static final String INVALID_REQUEST_OBJECT = "invalid_request_object";
    // The OP does not support use of the request parameter defined in Section 6.
    private static final String REQUEST_NOT_SUPPORTED = "request_not_supported";
    // The OP does not support use of the request_uri parameter defined in Section 6.
    private static final String REQUEST_URI_NOT_SUPPORTED = "request_uri_not_supported";
    // The OP does not support use of the registration parameter defined in Section 7.2.1.
    private static final String REGISTRATION_NOT_SUPPORTED = "registration_not_supported";

    public OpenIDError(String error, String errorDescription) {
        super(error, errorDescription);
    }
}
