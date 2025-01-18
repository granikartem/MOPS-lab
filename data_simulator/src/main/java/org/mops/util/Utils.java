package org.mops.util;

import java.net.http.HttpResponse;

public class Utils {
    public static boolean isSuccessful(HttpResponse response) {
        return response.statusCode() >= 200 && response.statusCode() < 300;
    }
}
