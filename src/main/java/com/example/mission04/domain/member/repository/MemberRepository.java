package com.example.mission04.domain.member.repository;

import com.example.mission04.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<Member> findByEmail(String username);
}
