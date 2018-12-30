package xyz.auguwu.http.factories;

import xyz.auguwu.http.HTTPRequest;
import xyz.auguwu.http.enums.HTTPRequestType;

public class RequestFactory {
    public static HTTPRequest get(String uri) {
        return new HTTPRequest(uri);
    }
    public static HTTPRequest post(String uri) {
        return new HTTPRequest(uri, HTTPRequestType.POST);
    }
}