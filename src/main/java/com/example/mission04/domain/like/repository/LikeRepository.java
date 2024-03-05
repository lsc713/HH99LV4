package com.example.mission04.domain.like.repository;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.like.entity.Like;
import com.example.mission04.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByMemberAndLecture(Member member, Lecture lecture);

    int countByLectureId(Long lectureId);
}
