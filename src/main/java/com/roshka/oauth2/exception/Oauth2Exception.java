package com.roshka.oauth2.exception;

import com.roshka.oauth2.objects.Oauth2Error;

import java.util.ArrayList;
import java.util.List;

public class Oauth2Exception extends Exception {

    private List<Oauth2Error> errorList;

    public Oauth2Exception(String message, List<Oauth2Error> errorList)
    {
        super(message);
        this.errorList = errorList;
    }

    public Oauth2Exception(String message, Oauth2Error error)
    {
        super(message);
        List<Oauth2Error> errors = new ArrayList<>();
        errors.add(error);
        setErrors(errors);
    }

    public List<Oauth2Error> getErrors() {
        return errorList;
    }

    public void setErrors(List<Oauth2Error> errorList) {
        this.errorList = errorList;
    }
}
