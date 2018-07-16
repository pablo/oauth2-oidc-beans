package com.roshka.openid.exception;

import com.roshka.oauth2.exception.Oauth2Exception;
import com.roshka.oauth2.objects.Oauth2Error;

import java.util.List;

public class OpenidException
    extends Oauth2Exception
{
    public OpenidException(String message, List<Oauth2Error> errorList) {
        super(message, errorList);
    }
}
