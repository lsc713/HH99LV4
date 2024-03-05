package com.example.mission04.domain.like.service;

import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.domain.like.entity.Like;
import com.example.mission04.domain.like.repository.LikeRepository;
import com.example.mission04.domain.member.entity.Member;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mission04.global.handler.exception.ErrorCode.LECTURE_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public boolean createLike(UserDetailsImpl userDetails, Long lectureId) {
        Member member = userDetails.getMember();
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new CustomApiException(LECTURE_ID_NOT_FOUND.getMessage()));
        Like like = likeRepository.findByMemberAndLecture(member, lecture);
        if (like != null) {
            likeRepository.delete(like);
            return false;
        } else {
            like = Like.builder()
                    .member(member)
                    .lecture(lecture)
                    .build();
            likeRepository.save(like);
            return true;
        }
    }


}
