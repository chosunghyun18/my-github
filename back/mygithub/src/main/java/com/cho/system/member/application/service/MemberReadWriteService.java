package com.cho.system.member.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cho.system.global.auth.jwt.response.TokenResponse;
import com.cho.system.global.auth.jwt.service.JwtProvider;
import com.cho.system.global.auth.jwt.service.JwtService;
import com.cho.system.infra.oauth2.api.TokenLoginResponse;
import com.cho.system.infra.oauth2.dto.OAuthAttributes;
import com.cho.system.member.domain.model.Member;
import com.cho.system.member.dto.MemberDto;
import com.cho.system.member.dto.MemberDtos.*;
import com.cho.system.member.dto.MemberNickNameHistoryDto;

@Service
@RequiredArgsConstructor
public class MemberReadWriteService implements MemberService {

    @Qualifier("memberReadService")
    private final MemberReadService memberReadService;

    @Qualifier("memberWrite")
    private final MemberWriteService memberWriteService;

    private final JwtProvider jwtProvider;
    private final JwtService jwtService;

    @Override
    public Member findByToken(String firebaseToken) {
        return memberReadService.findByToken(firebaseToken);
    }

    @Override
    public MemberDto getMember(Long memberId) {
        return memberReadService.getMember(memberId);
    }

    @Override
    public List<MemberDto> getMembers(List<Long> followingMemberIds) {
        return memberReadService.getMembers(followingMemberIds);
    }

    @Override
    public Member getByEmail(OAuthAttributes attributes) {
        return memberReadService.getByEmail(attributes);
    }

    @Override
    public Member findByEmail(String email) {
        return memberReadService.findByEmail(email);
    }

    @Override
    public Member loginWithPasswordRequest(MemberLoginRequestDto request) {
        return null;
    }

    @Override
    public Member findByNickName(String nickName) {
        return memberReadService.findByNickName(nickName);
    }

    @Override
    public boolean isNickRedundant(String nickName) {
        return memberReadService.isNickRedundant(nickName);
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        return memberReadService.getAllMembers();
    }

    @Override
    public List<MemberNickNameHistoryDto> getAllMemberHistory(Long memberId) {
        return memberReadService.getAllMemberHistory(memberId);
    }

    /*
     Member Write Service
     */
    public MemberRegisterResponse register(MemberPostRequestDto requestDto) {
        TokenResponse tokenResponse = createTokenReturn(requestDto.getEmail());
        MemberPostResponseDto result = createWithPassword(requestDto, tokenResponse);
        return new MemberRegisterResponse(result, tokenResponse);
    }

    private TokenResponse createTokenReturn(String email) {
        String accessToken = jwtProvider.getNewAccessToken(email);
        String refreshToken = jwtProvider.getNewRefreshToken(email);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public MemberPostResponseDto create(MemberOAuthRegisterRequestDto requestDto, TokenResponse tokenResponse) {
        return memberWriteService.create(requestDto, tokenResponse);
    }

    @Override
    public MemberPostResponseDto createWithPassword(MemberPostRequestDto requestDto, TokenResponse tokenResponse) {
        return memberWriteService.createWithPassword(requestDto, tokenResponse);
    }

    @Override
    public void deleteMember(Member member) {
        memberWriteService.deleteMember(member);
    }

    @Override
    public Member updateImageUrl(Member member, String imageUrl) {
        return memberWriteService.updateImageUrl(member, imageUrl);
    }

    @Override
    public Member updateNickName(Member member, String nickName) {
        return memberWriteService.updateNickName(member, nickName);
    }

    @Override
    public Member updateExpAndLevel(Member member, int addExpValue) {
        return memberWriteService.updateExpAndLevel(member, addExpValue);
    }

    @Override
    public void save(Member member) {
        memberWriteService.save(member);
    }

    @Override
    public Member saveByAttributes(OAuthAttributes attributes) {
        return memberWriteService.saveByAttributes(attributes);
    }

    @Transactional
    public TokenLoginResponse loginWithPassword(MemberLoginRequestDto request) {
        Member member = memberReadService.loginWithPasswordRequest(request);
        var response = jwtService.getTokenLoginResponseByMember(member);
        member.updateToken(response.tokenResponse().accessToken(), response.tokenResponse().refreshToken());
        return response;
    }

    public MemberRegisterResponse oauthRegister(MemberOAuthRegisterRequestDto requestDto) {
        TokenResponse tokenResponse = createTokenReturn(requestDto.getEmail());
        var result = create(requestDto, tokenResponse);
        return new MemberRegisterResponse(result, tokenResponse);
    }
}
