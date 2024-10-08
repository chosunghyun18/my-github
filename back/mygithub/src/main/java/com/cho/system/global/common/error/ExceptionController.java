package com.cho.system.global.common.error;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.ServiceUnavailableException;
import java.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import com.cho.system.global.auth.jwt.exception.InvalidTokenException;
import com.cho.system.global.common.dto.ApiResponse;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse ServerException2(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("서버 로직 에러",e.getMessage()); // 500
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse MissingRequestHeaderException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("MissingRequestHeaderException", e.getMessage()); // 400
    }
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse MissingPathVariableException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("잘못된 경로 입니다.", e.getMessage()); // 404
    }
    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse SignatureException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("SignatureException", e.getMessage()); //404
    }
    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse ServiceUnavailableExpection(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("Service Unavailable", e.getMessage());  //503
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse MssingServletRequestPartException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("파일 형식 혹은 파일 파라미터의 이름 오류 혹은 파일 없음", e.getMessage()); // 405
    }

    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse NoContentExpection(Exception e) {
       e.printStackTrace();
       return ApiResponse.error("No results content", e.getMessage() ); // 400
    }
    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse UnsupportedJwtException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("UnsupportedJwtException", e.getMessage()); // 401
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse MalformedJwtException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("MalformedJwtException",e.getMessage());//402
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse ExpiredJwtException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("ExpiredJwtException", e.getMessage()); // 403
    }
    @ExceptionHandler(JwtException.class)
    public ApiResponse JwtException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("JWTException", e.getMessage()); // 403
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ApiResponse JwtInvalidException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error("JWT 에러 터짐",e.getMessage());
    }
}
