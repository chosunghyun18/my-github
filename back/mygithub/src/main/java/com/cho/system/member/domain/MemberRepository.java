package com.cho.system.member.domain;

import java.util.List;
import java.util.Optional;
import com.cho.system.member.domain.model.Member;

public interface MemberRepository {

    Member save(String email, String atk, String rtk);

    Member saveWithPassWord(Member member, String atk, String rtk);

    Optional<Member> save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByNickName(String nickName);

    Optional<Member> findByEmail(String email);

    Optional<Member> findMemberForLogin(String email);

    void deleteMember(Member member);

    List<Member> findAll();

    List<Member> findAllByIdIn(List<Long> memberId);

}
