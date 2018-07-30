package com.roshka.oauth2.objects.builder;

import com.roshka.oauth2.exception.Oauth2Exception;
import com.roshka.oauth2.objects.Oauth2Error;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Oauth2ObjectsBuilderCtx
{
    private HttpServletRequest req;
    private List<Oauth2Error> errorList;

    public Oauth2ObjectsBuilderCtx(HttpServletRequest req)
    {
        this.req = req;
        errorList = new ArrayList<>();
    }

    public boolean hasErrors() {
        return errorList.size() > 0;
    }

    public List<Oauth2Error> getErrorList() {
        return errorList;
    }

    public String getSingleParameterValue(String paramName) {
        return this.getSingleParameterValue(paramName, false);
    }

    public String getSingleParameterValue(String paramName, boolean mandatory) {

        String[] values;
        String ret = null;
        if ((values = req.getParameterValues(paramName)) != null)
        {
            if (values.length == 1) {
                ret = values[0];
            } else {
                errorList.add(
                        new Oauth2Error(Oauth2Error.INVALID_REQUEST, String.format("Multiple (%d) values for parameter [%s]", values.length, paramName))
                );
            }
        } else {
            if (mandatory) {
                errorList.add(
                        new Oauth2Error(Oauth2Error.INVALID_REQUEST, String.format("Missing mandatory parameter [%s]", paramName))
                );
            }
        }

        return ret;

    }

    public void addError(Oauth2Error error) {
        errorList.add(error);
    }

    public Oauth2Exception getException()
    {
        if (this.hasErrors()) {
            // request is invalid
            int errCnt = this.getErrorList().size();
            String errorDescriptions = String.join("\n",
                    this.getErrorList().stream().map(Oauth2Error::getErrorDescription).collect(toList())
            );
            String message = String.format("%d %s found in AuthorizationRequest parsing:\n%s",
                    errCnt,
                    errCnt > 1 ? "errors" : "error",
                    errorDescriptions
            );
            return new Oauth2Exception(message, this.getErrorList());
        }
        return null;
    }


}
