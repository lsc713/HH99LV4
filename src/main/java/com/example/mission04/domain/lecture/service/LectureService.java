package com.example.mission04.domain.lecture.service;

import com.example.mission04.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.example.mission04.domain.lecture.dto.LectureResponseDto.ReadLectureResponseDto;
import com.example.mission04.domain.lecture.entity.Lecture;
import com.example.mission04.domain.lecture.entity.type.CategoryType;
import com.example.mission04.domain.lecture.repository.LectureRepository;
import com.example.mission04.domain.like.repository.LikeRepository;
import com.example.mission04.domain.member.repository.MemberRepository;
import com.example.mission04.global.handler.exception.CustomApiException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mission04.global.handler.exception.ErrorCode.MEMBER_ACCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public CreateLectureResponseDto createLecture(String email, CreateLectureRequestDto requestDto) {
        validateAuthority(email);
        Lecture lecture = lectureRepository.save(requestDto.toEntity());
        return new CreateLectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public ReadLectureResponseDto readLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new CustomApiException(MEMBER_ACCOUNT_NOT_FOUND.getMessage()));
        int likeCount = getLikeCount(id);
        return new ReadLectureResponseDto(lecture,likeCount);
    }


    @Transactional(readOnly = true)
    public Page<ReadLectureResponseDto> readLectureByCategory(CategoryType category, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Lecture> lectures = lectureRepository.findAllByCategory(category, pageable);
        return lectures.map(lecture -> {
            int likeCount = getLikeCount(lecture.getId());
            return new ReadLectureResponseDto(lecture,likeCount);
        });
    }

    @Transactional(readOnly = true)
    public int getLikeCount(Long lectureId) {
        return likeRepository.countByLectureId(lectureId);
    }

    private void validateAuthority(String email) {
        if (!memberRepository.existsByEmail(email)) {
            throw new CustomApiException(MEMBER_ACCOUNT_NOT_FOUND.getMessage());
        }
    }
}
