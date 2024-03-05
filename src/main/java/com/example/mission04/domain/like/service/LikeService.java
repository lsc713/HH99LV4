package com.example.mission04.domain.like.service;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.domain.like.entity.Like;
import com.example.mission04.domain.like.repository.LikeRepository;
import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;

    public LikeService(LikeRepository likeRepository, MemberRepository memberRepository, LectureRepository lectureRepository) {
        this.likeRepository = likeRepository;
        this.memberRepository = memberRepository;
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public AtomicReference<String> likes(String email, Long lectureId) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage())
        );
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new CustomApiException(ErrorCode.LECTURE_ID_NOT_FOUND.getMessage())
        );

        AtomicReference<String> result = new AtomicReference<>("");
        likeRepository.findByMemberAndLecture(member, lecture)
                .ifPresentOrElse(
                        like -> {
                            likeRepository.delete(like);
                            result.set("삭제");
                        },
                        () -> {
                            Like like = Like.builder()
                                    .member(member)
                                    .lecture(lecture)
                                    .build();

                            likeRepository.save(like);
                            result.set("추가");
                        }
                );
        return result; // 수정이 필요할 수 있음
    }
}
