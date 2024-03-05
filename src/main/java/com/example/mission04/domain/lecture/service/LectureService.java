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

import java.util.List;
import java.util.stream.Collectors;

import static com.example.mission04.global.handler.exception.ErrorCode.MEMBER_ACCOUNT_NOT_FOUND;

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
            throw new CustomApiException(MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ReadLectureResponseDto readLecture(Long id) {
        return new ReadLectureResponseDto(lectureRepository.findById(id).orElseThrow(() -> new CustomApiException(MEMBER_ACCOUNT_NOT_FOUND.getMessage())));
    }


    @Transactional(readOnly = true)
    public List<ReadLectureResponseDto> readLectureByCategory(String category) {
        List<Lecture> lectures = lectureRepository.findByCategory(category);
        return lectures.stream().map(ReadLectureResponseDto::new).collect(Collectors.toList());
    }
}
