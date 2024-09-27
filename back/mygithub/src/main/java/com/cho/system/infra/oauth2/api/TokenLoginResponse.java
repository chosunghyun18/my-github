package com.cho.system.infra.oauth2.api;

import com.cho.system.global.auth.jwt.response.TokenResponse;

public record TokenLoginResponse(String email, String type, String nickName, TokenResponse tokenResponse) {

}
