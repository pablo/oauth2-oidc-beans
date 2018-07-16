package com.roshka.oauth2.objects.builder;

import com.roshka.oauth2.exception.Oauth2Exception;
import com.roshka.oauth2.objects.AccessTokenRequest;
import com.roshka.oauth2.objects.AuthorizationRequest;
import com.roshka.oauth2.objects.Oauth2Error;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class AccessTokenRequestBuilder {


    public static void fromHttpServletRequest(AccessTokenRequest accessTokenRequest, HttpServletRequest request)
        throws Oauth2Exception
    {

        Oauth2ObjectsBuilderCtx bctx = new Oauth2ObjectsBuilderCtx(request);

        accessTokenRequest.setGrantType(bctx.getSingleParameterValue(AccessTokenRequest.GRANT_TYPE, true));
        accessTokenRequest.setCode(bctx.getSingleParameterValue(AccessTokenRequest.CODE, true));

        accessTokenRequest.setRedirectURI(bctx.getSingleParameterValue(AccessTokenRequest.REDIRECT_URI));
        accessTokenRequest.setClientId(bctx.getSingleParameterValue(AccessTokenRequest.CLIENT_ID));

        if (accessTokenRequest.getRedirectURI() != null) {
            // check if URI is valid
            try {
                URI uri = URI.create(accessTokenRequest.getRedirectURI());
            } catch (IllegalArgumentException e) {
                bctx.addError(
                    new Oauth2Error(
                        Oauth2Error.Oauth2ErrorType.INVALID_REQUEST,
                        String.format(
                                "Provided [%s] uri value is invalid. Error: [%s]",
                                AuthorizationRequest.REDIRECT_URI,
                                accessTokenRequest.getRedirectURI(),
                                e.getMessage()
                        )
                    )
                );
            }
        }

        if (bctx.hasErrors()) {
            throw bctx.getException();
        }

    }

    public static AccessTokenRequest fromHttpServletRequest(HttpServletRequest request)
        throws Oauth2Exception
    {
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        AccessTokenRequestBuilder.fromHttpServletRequest(accessTokenRequest, request);
        return accessTokenRequest;
    }

}

