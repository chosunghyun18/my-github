package com.cho.system.infra.oauth2.api;

import travelfeeldog.global.auth.jwt.response.TokenResponse;

public record TokenLoginResponse(String email, String type, String nickName, TokenResponse tokenResponse) {

}
