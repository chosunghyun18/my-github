package com.cho.system.member.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cho.system.member.domain.model.MemberNicknameHistory;

public interface MemberNicknameHistoryRepository extends JpaRepository<MemberNicknameHistory, Long> {

    List<MemberNicknameHistory> findAllByMemberId(Long memberId);

    List<MemberNicknameHistory> findAllByNickName(String nickName);


}
