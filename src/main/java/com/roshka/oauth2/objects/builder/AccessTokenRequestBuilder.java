package com.roshka.oauth2.objects.builder;

import com.roshka.oauth2.exception.Oauth2Exception;
import com.roshka.oauth2.objects.AccessTokenRequest;
import com.roshka.oauth2.objects.AuthorizationRequest;
import com.roshka.oauth2.objects.Oauth2Error;
import com.roshka.oauth2.util.URIUtil;

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
            if (!URIUtil.validURI(accessTokenRequest.getRedirectURI())) {
                bctx.addError(
                    new Oauth2Error(
                        Oauth2Error.INVALID_REQUEST,
                        String.format(
                                "Provided [%s] value [%s] value is invalid.",
                                AuthorizationRequest.REDIRECT_URI,
                                accessTokenRequest.getRedirectURI()
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

