package com.example.mission04.domain.lecture.service;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.*;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.*;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import com.example.mission04.global.handler.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public CraeteLectureResponseDto createLecture(String email, CreateLectureRequestDto requestDto) {
        validateAuthority(email);
        Lecture lecture = lectureRepository.save(requestDto.toEntity());
        return new CraeteLectureResponseDto(lecture);
    }

    private void validateAuthority(String email) {
        if (!lectureRepository.existsByEmail(email)) {
            throw new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
    }
}
