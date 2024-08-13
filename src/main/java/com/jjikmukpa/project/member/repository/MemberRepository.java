package com.jjikmukpa.project.member.repository;

import com.jjikmukpa.project.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findMemberByMemberId(String memberId);
    Optional<Member> findMemberByNameAndEmail(String name, String email);
    Optional<Member> findMemberByNameAndPhone(String name, String phone);

    boolean existsByMemberId(String memberId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByMemberIdAndNameAndEmail(String memberId, String name, String email);
    boolean existsByMemberIdAndNameAndPhone(String memberId, String name, String phone);

    Member findByMemberId(String memberId);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.memberPw = :modifiedPw WHERE m.memberId = :memberId")
    int updateMemberPwByMemberId(String memberId, String modifiedPw);
}
