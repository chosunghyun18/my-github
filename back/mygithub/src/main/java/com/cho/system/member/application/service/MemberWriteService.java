package com.cho.system.member.application.service;

import com.cho.system.global.auth.jwt.response.TokenResponse;
import com.cho.system.infra.oauth2.dto.OAuthAttributes;
import com.cho.system.member.domain.model.Member;
import com.cho.system.member.dto.MemberDtos.MemberOAuthRegisterRequestDto;
import com.cho.system.member.dto.MemberDtos.MemberPostRequestDto;
import com.cho.system.member.dto.MemberDtos.MemberPostResponseDto;

public interface MemberWriteService {
    MemberPostResponseDto create(MemberOAuthRegisterRequestDto requestDto, TokenResponse tokenResponse);

    MemberPostResponseDto createWithPassword(MemberPostRequestDto requestDto, TokenResponse tokenResponse);

    void deleteMember(Member member);

    Member updateImageUrl(Member member, String imageUrl);

    Member updateNickName(Member member, String nickName);

    Member updateExpAndLevel(Member member, int addExpValue);

    void save(Member member);

    Member saveByAttributes(OAuthAttributes attributes);
}
