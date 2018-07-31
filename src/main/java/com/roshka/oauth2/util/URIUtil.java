package com.roshka.oauth2.util;

import java.net.URI;
import java.net.URISyntaxException;

public class URIUtil {

    public static boolean validURI(String uri)
    {
        if (uri == null)
            return false;
        try {
            new URI(uri);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

}
