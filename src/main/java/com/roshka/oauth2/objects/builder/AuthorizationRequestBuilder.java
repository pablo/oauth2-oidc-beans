package com.roshka.oauth2.objects.builder;

import com.roshka.oauth2.exception.Oauth2Exception;
import com.roshka.oauth2.objects.AuthorizationRequest;
import com.roshka.oauth2.objects.Oauth2Error;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class AuthorizationRequestBuilder {


    public static void fromHttpServletRequest(AuthorizationRequest authorizationRequest, HttpServletRequest request)
        throws Oauth2Exception
    {

        Oauth2ObjectsBuilderCtx bctx = new Oauth2ObjectsBuilderCtx(request);

        authorizationRequest.setResponseType(bctx.getSingleParameterValue(AuthorizationRequest.RESPONSE_TYPE, true));
        authorizationRequest.setClientId(bctx.getSingleParameterValue(AuthorizationRequest.CLIENT_ID, true));
        authorizationRequest.setRedirectURI(bctx.getSingleParameterValue(AuthorizationRequest.REDIRECT_URI));

        if (authorizationRequest.getRedirectURI() != null) {
            // check if URI is valid
            try {
                URI uri = URI.create(authorizationRequest.getRedirectURI());
            } catch (IllegalArgumentException e) {
                bctx.addError(
                    new Oauth2Error(
                        Oauth2Error.Oauth2ErrorType.INVALID_REQUEST,
                        String.format(
                                "Provided [%s] uri value is invalid. Error: [%s]",
                                AuthorizationRequest.REDIRECT_URI,
                                authorizationRequest.getRedirectURI(),
                                e.getMessage()
                        )
                    )
                );
            }
        }

        authorizationRequest.setScope(bctx.getSingleParameterValue(AuthorizationRequest.SCOPE, true));
        authorizationRequest.setState(bctx.getSingleParameterValue(AuthorizationRequest.STATE));
        authorizationRequest.setResponseMode(bctx.getSingleParameterValue(AuthorizationRequest.RESPONSE_MODE));

        if (bctx.hasErrors()) {
            throw bctx.getException();
        }

    }

    public static AuthorizationRequest fromHttpServletRequest(HttpServletRequest request)
        throws Oauth2Exception
    {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest();
        AuthorizationRequestBuilder.fromHttpServletRequest(authorizationRequest, request);
        return authorizationRequest;
    }

}

