package com.cho.system.member.application.usecase;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cho.system.member.application.service.FollowReadService;
import com.cho.system.member.application.service.MemberReadService;
import com.cho.system.member.domain.model.Follow;
import com.cho.system.member.dto.MemberDto;

@RequiredArgsConstructor
@Service
public class GetFollowingMembersUsecase {
    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
