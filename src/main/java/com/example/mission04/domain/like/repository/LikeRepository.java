package com.example.mission04.domain.like.repository;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.like.entity.Like;
import com.example.mission04.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByMemberAndLecture(Member member, Lecture lecture);

    Long countByLecture(Lecture lecture);
}
