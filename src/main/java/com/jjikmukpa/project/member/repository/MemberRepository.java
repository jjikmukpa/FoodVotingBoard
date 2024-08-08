package com.jjikmukpa.project.member.repository;

import com.jjikmukpa.project.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findMemberByMemberId(String memberId);

    boolean existsByMemberId(String memberId);
    boolean existsByNickname(String nickname);
}
