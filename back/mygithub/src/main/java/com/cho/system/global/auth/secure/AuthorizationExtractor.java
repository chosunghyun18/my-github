package com.cho.system.global.auth.secure;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationExtractor {
    public static final String AUTHORIZATION = "Authorization";
    private AuthorizationExtractor() {
    }

    public static String extract(HttpServletRequest request) {
        Enumeration<String> headers =request.getHeaders(AUTHORIZATION);
        while (headers.hasMoreElements()) {
            String token = headers.nextElement();
            log.info("Header Value: '{}'", token);
            return token;
        }
        return null;
    }
}
