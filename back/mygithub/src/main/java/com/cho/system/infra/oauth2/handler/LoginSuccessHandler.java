package com.cho.system.infra.oauth2.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;
import com.cho.system.global.auth.jwt.service.JwtService;

@RequiredArgsConstructor
@Slf4j
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String REDIRECT_URL = "/";
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String email = extractUsername(authentication);
        jwtService.tokenUpdateCheckByEmail(email);
        getRedirectStrategy().sendRedirect(request, response, getRedirectUrl(email));
    }

    private String extractUsername(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return (String) attributes.get("email");
    }

    private String getRedirectUrl(String email) {
        return UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam("Authorization", jwtService.getNewAuthTokenByEmail(email))
                .build().toUriString();
    }
}
