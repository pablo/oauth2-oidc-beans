package com.roshka.openid.objects.builder;


import com.roshka.oauth2.exception.Oauth2Exception;
import com.roshka.oauth2.objects.builder.AuthorizationRequestBuilder;
import com.roshka.oauth2.objects.builder.Oauth2ObjectsBuilderCtx;
import com.roshka.openid.objects.AuthenticationRequest;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationRequestBuilder {

    public static void fromHttpServletRequest(AuthenticationRequest authenticationRequest, HttpServletRequest request)
            throws Oauth2Exception
    {
        // parse Oauth2 authorization request parts
        // if Oauth2 params are invalid, an exception will be thrown at this point
        AuthorizationRequestBuilder.fromHttpServletRequest(authenticationRequest, request);

        // specific AuthenticationRequest parameters follow
        Oauth2ObjectsBuilderCtx bctx = new Oauth2ObjectsBuilderCtx(request);

        authenticationRequest.setNonce(bctx.getSingleParameterValue(AuthenticationRequest.NONCE));
        authenticationRequest.setDisplay(bctx.getSingleParameterValue(AuthenticationRequest.DISPLAY));
        authenticationRequest.setPrompt(bctx.getSingleParameterValue(AuthenticationRequest.PROMPT));
        authenticationRequest.setMaxAge(bctx.getSingleParameterValue(AuthenticationRequest.MAX_AGE));
        authenticationRequest.setClaimLocales(bctx.getSingleParameterValue(AuthenticationRequest.CLAIM_LOCALES));
        authenticationRequest.setUiLocales(bctx.getSingleParameterValue(AuthenticationRequest.UI_LOCALES));
        authenticationRequest.setIdTokenHint(bctx.getSingleParameterValue(AuthenticationRequest.ID_TOKEN_HINT));
        authenticationRequest.setLoginHint(bctx.getSingleParameterValue(AuthenticationRequest.LOGIN_HINT));
        authenticationRequest.setAcrValues(bctx.getSingleParameterValue(AuthenticationRequest.ACR_VALUES));

    }


    public static AuthenticationRequest fromHttpServletRequest(HttpServletRequest request)
            throws Oauth2Exception
    {
        AuthenticationRequest authenticationReq =  new AuthenticationRequest();
        AuthenticationRequestBuilder.fromHttpServletRequest(authenticationReq, request);
        return authenticationReq;
    }


}
