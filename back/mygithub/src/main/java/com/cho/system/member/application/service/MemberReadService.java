package com.cho.system.member.application.service;

import java.util.List;
import com.cho.system.infra.oauth2.dto.OAuthAttributes;
import com.cho.system.member.domain.model.Member;
import com.cho.system.member.dto.MemberDto;
import com.cho.system.member.dto.MemberDtos.MemberLoginRequestDto;
import com.cho.system.member.dto.MemberDtos.MemberResponse;
import com.cho.system.member.dto.MemberNickNameHistoryDto;

public interface MemberReadService {
    Member findByToken(String accessToken);

    Member findByNickName(String nickName);

    boolean isNickRedundant(String nickName);

    List<MemberResponse> getAllMembers();

    List<MemberNickNameHistoryDto> getAllMemberHistory(Long memberId);

    MemberDto getMember(Long fromMemberId);

    List<MemberDto> getMembers(List<Long> followingMemberIds);

    Member getByEmail(OAuthAttributes attributes);

    Member findByEmail(String email);

    Member loginWithPasswordRequest(MemberLoginRequestDto request);
}
